package edu.umd.lib.grails.services

import org.springframework.dao.DataIntegrityViolationException

class InitialsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [initialsInstanceList: Initials.list(params), initialsInstanceTotal: Initials.count()]
    }

    def create() {
        [initialsInstance: new Initials(params)]
    }

    def save() {
        def initialsInstance = new Initials(params)
        if (!initialsInstance.save(flush: true)) {
            render(view: "create", model: [initialsInstance: initialsInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'initials.label', default: 'Initials'), initialsInstance.id])
        redirect(action: "show", id: initialsInstance.id)
    }

    def show() {
        def initialsInstance = Initials.get(params.id)
        if (!initialsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'initials.label', default: 'Initials'), params.id])
            redirect(action: "list")
            return
        }

        [initialsInstance: initialsInstance]
    }

    def edit() {
        def initialsInstance = Initials.get(params.id)
        if (!initialsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'initials.label', default: 'Initials'), params.id])
            redirect(action: "list")
            return
        }

        [initialsInstance: initialsInstance]
    }

    def update() {
        def initialsInstance = Initials.get(params.id)
        if (!initialsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'initials.label', default: 'Initials'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (initialsInstance.version > version) {
                initialsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'initials.label', default: 'Initials')] as Object[],
                          "Another user has updated this Initials while you were editing")
                render(view: "edit", model: [initialsInstance: initialsInstance])
                return
            }
        }

        initialsInstance.properties = params

        if (!initialsInstance.save(flush: true)) {
            render(view: "edit", model: [initialsInstance: initialsInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'initials.label', default: 'Initials'), initialsInstance.id])
        redirect(action: "show", id: initialsInstance.id)
    }

    def delete() {
        def initialsInstance = Initials.get(params.id)
        if (!initialsInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'initials.label', default: 'Initials'), params.id])
            redirect(action: "list")
            return
        }

        try {
            initialsInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'initials.label', default: 'Initials'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'initials.label', default: 'Initials'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
