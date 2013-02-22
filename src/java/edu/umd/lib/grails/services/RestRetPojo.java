package edu.umd.lib.grails.services;



public class RestRetPojo {

	private String filename;

	public RestRetPojo() {
		super();
	}

	public RestRetPojo(String filename) {
		super();
		this.filename = filename;
	}
	
	public RestRetPojo(AutoNumber autoNumber) {
		super();
		this.filename = autoNumber.getRepository() + "-" + autoNumber.getId() + "-0001";
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
