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
		def map = [repos : repoUnion,
		 autoNumberInstance: new AutoNumber(params) ]
		render (view : "create2", model : map)
	}

	def save2() {
		def repo = Repository.findByRepositoryName(params.repository)
		
		if(repo == null) {
			repo = new Repository(repositoryName : params.repository)
			repo.save(failOnError : true, flush: true)
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
			
			def map = [repos : repoUnion,
				fileName : restRetPojo.getFilename(), autoNumberInstance: retVal ]
			render (view : "create2", model : map)
		}
	}
	
	def getRepos () {
		
		def dbReps = Repository.list(sort:'repositoryName',order:'asc')
		
		return dbReps
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
