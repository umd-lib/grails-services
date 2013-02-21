package edu.umd.lib.grails.services

import java.util.Date;

import groovy.transform.ToString

@ToString(includeNames=true)
class FormScan {

    static constraints = {
		repository (nullable: true)
		initials  (nullable: true)
		entryDate (nullable: true)
    }
	
//	static mapping = {
//		table 'frmScan'
//		version false
//		id generator: 'hilo',
//		   params: [table: 'FRMSCAN',
//					column: 'autoNumber',
//					max_lo: 100]
//	}
	static mapping = {
		table 'FrmScan'
		version false
		id column: 'frmscan_id'
	}
	
//	Integer autoNumber;
	String repository;
	String initials;
	Date entryDate;
}
