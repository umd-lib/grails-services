package edu.umd.lib.grails.services

import org.springframework.dao.DataIntegrityViolationException

class RepositoryController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [repositoryInstanceList: Repository.list(params), repositoryInstanceTotal: Repository.count()]
    }

    def create() {
        [repositoryInstance: new Repository(params)]
    }

    def save() {
        def repositoryInstance = new Repository(params)
        if (!repositoryInstance.save(flush: true)) {
            render(view: "create", model: [repositoryInstance: repositoryInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'repository.label', default: 'Repository'), repositoryInstance.id])
        redirect(action: "show", id: repositoryInstance.id)
    }

    def show() {
        def repositoryInstance = Repository.get(params.id)
        if (!repositoryInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'repository.label', default: 'Repository'), params.id])
            redirect(action: "list")
            return
        }

        [repositoryInstance: repositoryInstance]
    }

    def edit() {
        def repositoryInstance = Repository.get(params.id)
        if (!repositoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'repository.label', default: 'Repository'), params.id])
            redirect(action: "list")
            return
        }

        [repositoryInstance: repositoryInstance]
    }

    def update() {
        def repositoryInstance = Repository.get(params.id)
        if (!repositoryInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'repository.label', default: 'Repository'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (repositoryInstance.version > version) {
                repositoryInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'repository.label', default: 'Repository')] as Object[],
                          "Another user has updated this Repository while you were editing")
                render(view: "edit", model: [repositoryInstance: repositoryInstance])
                return
            }
        }

        repositoryInstance.properties = params

        if (!repositoryInstance.save(flush: true)) {
            render(view: "edit", model: [repositoryInstance: repositoryInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'repository.label', default: 'Repository'), repositoryInstance.id])
        redirect(action: "show", id: repositoryInstance.id)
    }

    def delete() {
        def repositoryInstance = Repository.get(params.id)
        if (!repositoryInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'repository.label', default: 'Repository'), params.id])
            redirect(action: "list")
            return
        }

        try {
            repositoryInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'repository.label', default: 'Repository'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'repository.label', default: 'Repository'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
