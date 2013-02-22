package edu.umd.lib.grails.services

import grails.converters.JSON
import grails.converters.XML

class RestAutoNumberController {

//	static allowedMethods = [show: "GET", save: "PUT", update: "POST", delete: "DELETE"]
	
	static {
		grails.converters.JSON.registerObjectMarshaller(RestRetPojo) {
			return it.properties.findAll {k,v -> k != 'class'}
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
							file(retObject.filename)
						}
					}
				}
			}
		}
//		render retObjects
	}

	def save() {
		def newRec = new AutoNumber(initials: params.initials, repository : params.repository, entryDate: new Date())
		def retVal = newRec.save()
		if(retVal == null) {
			response.status = 404;
			render "Failed to update DataBase"
		} else {
			def restRetPojo = new RestRetPojo (retVal)
			request.withFormat {				
				json { 
					render restRetPojo as JSON 
				}
				xml { 
					render { 
						files { 
							for ( retObject in retObjects ) {
								file(retObject.filename)
							}
						}
					}
				}
			}
//			render new RestRetPojo (retVal) 
		}
	}
	
	def delete() {
		response.status = 403;
		response.outputStream << "Delete request not supported"
	}


}
