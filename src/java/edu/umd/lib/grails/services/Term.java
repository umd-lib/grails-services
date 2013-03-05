package edu.umd.lib.grails.services;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Term {
	String term;

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	
}
