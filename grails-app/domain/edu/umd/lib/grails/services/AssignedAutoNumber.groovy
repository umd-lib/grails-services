package edu.umd.lib.grails.services

import groovy.transform.ToString


class AssignedAutoNumber {

    static constraints = {
//		repository (nullable: true)
//		initials  (nullable: true)
		entryDate (nullable: true)
    }
	
	static mapping = {
		table 'autoNumber'
		version false
		id generator: 'assigned', column: 'autoNumber_id' 
		repository lazy: false
	}
	
//	static belongsTo = [repository: Repository, initials Initials]
	
	Long id 
	Repository repository
	Initials initials
	Date entryDate
	
}
