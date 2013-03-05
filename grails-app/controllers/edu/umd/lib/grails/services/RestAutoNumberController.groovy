package edu.umd.lib.grails.services

import grails.converters.JSON
import grails.converters.XML
class RestAutoNumberController {
	
	static {
		grails.converters.JSON.registerObjectMarshaller(RestRetPojo) {
			return it.properties.findAll {k,v -> k != "class" && k != 'class'}
		}
	}

	
	def show() {
		
		if(log.debugEnabled) {
			log.debug params	
			log.debug "Header " + request.getHeader("Accept")
			log.debug "Request Format " + request.getFormat()
			log.debug "Response Format " + response.getFormat()
		}
		def recs = AutoNumber.findAllWhere(initials: params.initials, repository : params.repository)
		
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
		def newRec = new AutoNumber(initials: params.initials, repository : params.repository, entryDate: new Date())
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
		def map = [repos : Repository.list(sort:'repository',order:'asc') ] //['bcast', 'bna', 'histmss', 'litmss', 'map', 'md', 'ntl', 'rare', 'scpa', 'univarch', 'usgov'] ]
		render (view : "/restAutoNumber/autoNumberForm", model : map)
	}
	
	def saveForm() {
		def newRec = new AutoNumber(initials: params.initials, repository : new Repository(params.repositoryInput), entryDate: new Date())
		def retVal = newRec.save(flush: true)
		if(retVal == null) {
			response.status = 404;
			render "Failed to update DataBase"
		} else {
			def restRetPojo = new RestRetPojo (retVal)
			def map = [repos :  Repository.list(sort:'repository',order:'asc'), //['bcast', 'bna', 'histmss', 'litmss', 'map', 'md', 'ntl', 'rare', 'scpa', 'univarch', 'usgov'],
				fileName : restRetPojo.getFilename() ]
			render (view : "/restAutoNumber/autoNumberForm", model : map)
		}
	}
}
