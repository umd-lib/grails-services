package edu.umd.lib.grails.services;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="response")
@XmlAccessorType( XmlAccessType.FIELD )
public class Response {
		String message;
		List<TermList> list = new ArrayList<TermList>();
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<TermList> getList() {
			return list;
		}
		public void setList(List<TermList> list) {
			this.list = list;
		}
		
		
		
}


