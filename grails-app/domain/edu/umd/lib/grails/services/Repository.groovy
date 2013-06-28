package edu.umd.lib.grails.services

import groovy.transform.ToString


class Repository {

    static constraints = {
		repositoryName (unique: true  )
		repositoryName column: 'repositoryName', index: 'repositoryName_Idx'
    }
	
	static mapping = {
		table 'repository'
		version false
	}
	
	static hasMany = [
		autoNumber : AutoNumber
	]
	
	String repositoryName
	
	String toString () {
		return repositoryName
	}
}
