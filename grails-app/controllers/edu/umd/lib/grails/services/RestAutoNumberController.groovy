package edu.umd.lib.grails.services

class RestAutoNumberController {

//	static allowedMethods = [show: "GET", save: "PUT", update: "POST", delete: "DELETE"]
	
	def show() {
		println params
		render 'hello'
	}

	def update() {
		println params
		render 'hello'
	}


	def save() {
		println params
		render 'hello'
	}
	
	def delete() {
		println params
		render 'hello'
	}
}
