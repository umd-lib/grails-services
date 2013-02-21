package edu.umd.lib.grails.services

import org.springframework.dao.DataIntegrityViolationException

class FormScanController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [formScanInstanceList: FormScan.list(params), formScanInstanceTotal: FormScan.count()]
    }

    def create() {
        [formScanInstance: new FormScan(params)]
    }

    def save() {
        def formScanInstance = new FormScan(params)
        if (!formScanInstance.save(flush: true)) {
            render(view: "create", model: [formScanInstance: formScanInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'formScan.label', default: 'FormScan'), formScanInstance.id])
        redirect(action: "show", id: formScanInstance.id)
    }

    def show(Long id) {
        def formScanInstance = FormScan.get(id)
        if (!formScanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formScan.label', default: 'FormScan'), id])
            redirect(action: "list")
            return
        }

        [formScanInstance: formScanInstance]
    }

    def edit(Long id) {
        def formScanInstance = FormScan.get(id)
        if (!formScanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formScan.label', default: 'FormScan'), id])
            redirect(action: "list")
            return
        }

        [formScanInstance: formScanInstance]
    }

    def update(Long id, Long version) {
        def formScanInstance = FormScan.get(id)
        if (!formScanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formScan.label', default: 'FormScan'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (formScanInstance.version > version) {
                formScanInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'formScan.label', default: 'FormScan')] as Object[],
                          "Another user has updated this FormScan while you were editing")
                render(view: "edit", model: [formScanInstance: formScanInstance])
                return
            }
        }

        formScanInstance.properties = params

        if (!formScanInstance.save(flush: true)) {
            render(view: "edit", model: [formScanInstance: formScanInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'formScan.label', default: 'FormScan'), formScanInstance.id])
        redirect(action: "show", id: formScanInstance.id)
    }

    def delete(Long id) {
        def formScanInstance = FormScan.get(id)
        if (!formScanInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formScan.label', default: 'FormScan'), id])
            redirect(action: "list")
            return
        }

        try {
            formScanInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'formScan.label', default: 'FormScan'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'formScan.label', default: 'FormScan'), id])
            redirect(action: "show", id: id)
        }
    }
}
