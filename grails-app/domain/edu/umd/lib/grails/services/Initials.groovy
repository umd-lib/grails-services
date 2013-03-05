package edu.umd.lib.grails.services

import groovy.transform.ToString


class Initials {

    static constraints = {
		initialsName (unique: true  )
		initialsName column: 'initialsName', index: 'initialsName_Idx'
    }
	
	static mapping = {
		table 'initials'
		version false
	}
	
	static hasMany = [
		autoNumber : AutoNumber
	]
	
	String initialsName
	
	String toString() {
		return initialsName
	}
}
