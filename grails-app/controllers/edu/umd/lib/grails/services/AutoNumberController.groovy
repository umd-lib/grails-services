package edu.umd.lib.grails.services

import javax.xml.parsers.FactoryConfigurationError

import org.springframework.dao.DataIntegrityViolationException

class AutoNumberController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def repoFetchUrl
	def repoUpdateUrl
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [autoNumberInstanceList: AutoNumber.list(params), autoNumberInstanceTotal: AutoNumber.count()]
    }

    def create() {
        [autoNumberInstance: new AutoNumber(params)]
    }
	
	def create2() {
		def repoUnion = getRepos ()	
		def map = [repos : repoUnion , //['bcast', 'bna', 'histmss', 'litmss', 'map', 'md', 'ntl', 'rare', 'scpa', 'univarch', 'usgov'],
		 autoNumberInstance: new AutoNumber(params) ]
		render (view : "create2", model : map)
	}

	def save2() {
		println params
		def repo = Repository.findByRepositoryName(params.repository)
		
		if(repo == null) {
			repo = new Repository(repositoryName : params.repository)
			repo.save(failOnError : true, flush: true)
			setRepos (params.repository)
		}
		
		def init = Initials.findByInitialsName(params.initials)
		
		if(init == null) {
			init = new Initials(initialsName : params.initials)
			init.save(failOnError : true, flush: true)
		}
		
		def newRec = new AutoNumber(initials: init, repository : repo, entryDate: new Date())
		def retVal = newRec.save(failOnError : true, flush: true)
		if(retVal == null) {
			response.status = 404;
			render "Failed to update DataBase"
		} else {
			def restRetPojo = new RestRetPojo (retVal)
			
			def repoUnion = getRepos ()
			
			def map = [repos : repoUnion, //Repository.list(sort:'repositoryName',order:'asc'),//['bcast', 'bna', 'histmss', 'litmss', 'map', 'md', 'ntl', 'rare', 'scpa', 'univarch', 'usgov'],
				fileName : restRetPojo.getFilename(), autoNumberInstance: retVal ]
			render (view : "create2", model : map)
		}
	}
	
	def setRepos (String term) {
		org.springframework.web.client.RestTemplate rt = new org.springframework.web.client.RestTemplate()
		def updateUrl =  repoUpdateUrl as String
		updateUrl = updateUrl.replaceFirst('\\{term\\}', term)
		log.debug(updateUrl)
		try {
			org.springframework.http.ResponseEntity rp2 = rt.getForEntity(updateUrl, edu.umd.lib.grails.services.Response.class)
		} catch (Exception e) {
			log.error("Problem setting repos in fedora", e)
		} catch (FactoryConfigurationError e) {
			log.error("Problem getting repos from fedora", e)
		}
	}
	
	def getRepos () {
		
		edu.umd.lib.grails.services.Response rp3 = null;
		try {
			org.springframework.web.client.RestTemplate rt = new org.springframework.web.client.RestTemplate()
			org.springframework.http.ResponseEntity rp2 = rt.getForEntity(repoFetchUrl, edu.umd.lib.grails.services.Response.class)
			rp3 = (edu.umd.lib.grails.services.Response)rp2.getBody()
		} catch (Exception e) {
			log.error("Problem getting repos from fedora", e)
		} catch (FactoryConfigurationError e) {
			log.error("Problem getting repos from fedora", e)
		}
	
		def dbReps = Repository.list(sort:'repositoryName',order:'asc')
		def repoUnion = new TreeSet()
		
		if(dbReps != null) {
			for(Repository dbRep : dbReps) {
				repoUnion.add(dbRep.repositoryName)
			}
		}
		
		if(rp3 != null && rp3.getList() != null && rp3.getList().get(0) != null) {
			for(String frep : rp3.getList().get(0).getTerms()) {
				repoUnion.add(frep)
			}
		}
		
		return repoUnion
	}

    def save() {
        def autoNumberInstance = new AutoNumber(params)
        if (!autoNumberInstance.save(flush: true)) {
            render(view: "create", model: [autoNumberInstance: autoNumberInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), autoNumberInstance.id])
        redirect(action: "show", id: autoNumberInstance.id)
    }

    def show() {
        def autoNumberInstance = AutoNumber.get(params.id)
        if (!autoNumberInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), params.id])
            redirect(action: "list")
            return
        }

        [autoNumberInstance: autoNumberInstance]
    }

    def edit() {
        def autoNumberInstance = AutoNumber.get(params.id)
        if (!autoNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), params.id])
            redirect(action: "list")
            return
        }

        [autoNumberInstance: autoNumberInstance]
    }

    def update() {
        def autoNumberInstance = AutoNumber.get(params.id)
        if (!autoNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (autoNumberInstance.version > version) {
                autoNumberInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'autoNumber.label', default: 'AutoNumber')] as Object[],
                          "Another user has updated this AutoNumber while you were editing")
                render(view: "edit", model: [autoNumberInstance: autoNumberInstance])
                return
            }
        }

        autoNumberInstance.properties = params

        if (!autoNumberInstance.save(flush: true)) {
            render(view: "edit", model: [autoNumberInstance: autoNumberInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), autoNumberInstance.id])
        redirect(action: "show", id: autoNumberInstance.id)
    }

    def delete() {
        def autoNumberInstance = AutoNumber.get(params.id)
        if (!autoNumberInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), params.id])
            redirect(action: "list")
            return
        }

        try {
            autoNumberInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
