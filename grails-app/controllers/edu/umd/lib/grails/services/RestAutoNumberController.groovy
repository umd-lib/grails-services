package edu.umd.lib.grails.services

import grails.converters.JSON

import javax.naming.InitialContext
class RestAutoNumberController {
	
	static {
		grails.converters.JSON.registerObjectMarshaller(RestRetPojo) {
			return it.properties.findAll {k,v -> k != "class" && k != 'class'}
		}
	}

	def repoFetchUrl
	def repoUpdateUrl
	
	
	def show() {
		
		if(log.debugEnabled) {
			log.debug params	
			log.debug "Header " + request.getHeader("Accept")
			log.debug "Request Format " + request.getFormat()
			log.debug "Response Format " + response.getFormat()
		}
		
		def init = Initials.findByInitialsName(params.initials)
		def repo = Repository.findByRepositoryName(params.repository)
		
		def recs = AutoNumber.findAllWhere(initials: init, repository : repo)
		
		def retObjects = []
		for ( rec in recs ) {
			retObjects.add(new RestRetPojo (rec))
		}
	
		response.withFormat {
			json { 
				render retObjects as JSON 
			}
			xml { 
				render { 
					files { 
						for ( retObject in retObjects ) {
							file(filename: retObject.filename)
						}
					}
				}
			}
		}
	}

	def save() {
		def repo = Repository.findByRepositoryName(params.repository)
		
		if(repo == null) {
			repo = new Repository(RepositoryName : params.repository)
			repo.save()
			setRepos (params.repository)
		}
		
		def init = Initials.findByInitialsName(params.initials)
		
		if(init == null) {
			init = new Initials(InitialsName : params.initials)
			init.save()
		}
		
		def newRec = new AutoNumber(initials: init, repository : repo, entryDate: new Date())
		
		def retVal = newRec.save()
		if(retVal == null) {
			response.status = 404;
			render "Failed to update DataBase"
		} else {
			def restRetPojo = new RestRetPojo (retVal)
			response.withFormat {				
				json { 
					render restRetPojo as JSON 
				}
				xml { 
					render { 
						files { 
							file(filename: restRetPojo.filename)
						}
					}
				}
			}
		}
	}
	
	def delete() {
		response.status = 403;
		response.outputStream << "Delete request not supported"
	}

	def displayForm() {
		def repoUnion = getRepos ()
		
		def map = [repos : repoUnion ] //Repository.list(sort:'repository',order:'asc') ] //['bcast', 'bna', 'histmss', 'litmss', 'map', 'md', 'ntl', 'rare', 'scpa', 'univarch', 'usgov'] ]
		render (view : "/restAutoNumber/autoNumberForm", model : map)
	}
	
	def saveForm() {
//		println repoUrl
		def repo = Repository.findByRepositoryName(params.repository)
		
		if(repo == null) {
			repo = new Repository(RepositoryName : params.repository)
			repo.save()
			setRepos (params.repository)
		}
		
		def init = Initials.findByInitialsName(params.initials)
		
		if(init == null) {
			init = new Initials(InitialsName : params.initials)
			init.save()
		}
		
		def newRec = new AutoNumber(initials: init, repository : repo, entryDate: new Date())
		def retVal = newRec.save(flush: true)
		if(retVal == null) {
			response.status = 404;
			render "Failed to update DataBase"
		} else {
			def restRetPojo = new RestRetPojo (retVal)
			def repoUnion = getRepos ()
			def map = [repos : repoUnion, // Repository.list(sort:'repository',order:'asc'), //['bcast', 'bna', 'histmss', 'litmss', 'map', 'md', 'ntl', 'rare', 'scpa', 'univarch', 'usgov'],
				fileName : restRetPojo.getFilename() ]
			render (view : "/restAutoNumber/autoNumberForm", model : map)
		}
	}
	
	def setRepos (String term) {
		org.springframework.web.client.RestTemplate rt = new org.springframework.web.client.RestTemplate()
		def updateUrl =  repoUpdateUrl as String 
		updateUrl = updateUrl.replaceFirst('\\{term\\}', term)
		log.debug(updateUrl)
		org.springframework.http.ResponseEntity rp2 = rt.getForEntity(updateUrl, edu.umd.lib.grails.services.Response.class)
	}
	
	
	def getRepos () {
		org.springframework.web.client.RestTemplate rt = new org.springframework.web.client.RestTemplate()
		org.springframework.http.ResponseEntity rp2 = rt.getForEntity(repoFetchUrl, edu.umd.lib.grails.services.Response.class)
		edu.umd.lib.grails.services.Response rp3 = (edu.umd.lib.grails.services.Response)rp2.getBody()
	
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
}
