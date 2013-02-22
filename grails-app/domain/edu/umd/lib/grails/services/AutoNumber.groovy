package edu.umd.lib.grails.services

import java.util.Date;

import groovy.transform.ToString

@ToString(includeNames=true)
class AutoNumber {

    static constraints = {
		repository (nullable: true)
		initials  (nullable: true)
		entryDate (nullable: true)
    }
	
	static mapping = {
		table 'autoNumber'
		version false
		id column: 'autoNumber_id' 
	}
	
	Long id 
	String repository
	String initials
	Date entryDate
}
