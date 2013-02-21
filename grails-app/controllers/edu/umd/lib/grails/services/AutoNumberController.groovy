package edu.umd.lib.grails.services

import org.springframework.dao.DataIntegrityViolationException

class AutoNumberController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [autoNumberInstanceList: AutoNumber.list(params), autoNumberInstanceTotal: AutoNumber.count()]
    }

    def create() {
        [autoNumberInstance: new AutoNumber(params)]
    }

    def save() {
        def autoNumberInstance = new AutoNumber(params)
        if (!autoNumberInstance.save(flush: true)) {
            render(view: "create", model: [autoNumberInstance: autoNumberInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), autoNumberInstance.id])
        redirect(action: "show", id: autoNumberInstance.id)
    }

    def show(Long id) {
        def autoNumberInstance = AutoNumber.get(id)
        if (!autoNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), id])
            redirect(action: "list")
            return
        }

        [autoNumberInstance: autoNumberInstance]
    }

    def edit(Long id) {
        def autoNumberInstance = AutoNumber.get(id)
        if (!autoNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), id])
            redirect(action: "list")
            return
        }

        [autoNumberInstance: autoNumberInstance]
    }

    def update(Long id, Long version) {
        def autoNumberInstance = AutoNumber.get(id)
        if (!autoNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (autoNumberInstance.version > version) {
                autoNumberInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'autoNumber.label', default: 'AutoNumber')] as Object[],
                          "Another user has updated this AutoNumber while you were editing")
                render(view: "edit", model: [autoNumberInstance: autoNumberInstance])
                return
            }
        }

        autoNumberInstance.properties = params

        if (!autoNumberInstance.save(flush: true)) {
            render(view: "edit", model: [autoNumberInstance: autoNumberInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), autoNumberInstance.id])
        redirect(action: "show", id: autoNumberInstance.id)
    }

    def delete(Long id) {
        def autoNumberInstance = AutoNumber.get(id)
        if (!autoNumberInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), id])
            redirect(action: "list")
            return
        }

        try {
            autoNumberInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'autoNumber.label', default: 'AutoNumber'), id])
            redirect(action: "show", id: id)
        }
    }
}
