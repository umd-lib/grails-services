package edu.umd.lib.grails.services



import org.junit.*
import grails.test.mixin.*

@TestFor(FormScanController)
@Mock(FormScan)
class FormScanControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/formScan/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.formScanInstanceList.size() == 0
        assert model.formScanInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.formScanInstance != null
    }

    void testSave() {
        controller.save()

        assert model.formScanInstance != null
        assert view == '/formScan/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/formScan/show/1'
        assert controller.flash.message != null
        assert FormScan.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/formScan/list'

        populateValidParams(params)
        def formScan = new FormScan(params)

        assert formScan.save() != null

        params.id = formScan.id

        def model = controller.show()

        assert model.formScanInstance == formScan
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/formScan/list'

        populateValidParams(params)
        def formScan = new FormScan(params)

        assert formScan.save() != null

        params.id = formScan.id

        def model = controller.edit()

        assert model.formScanInstance == formScan
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/formScan/list'

        response.reset()

        populateValidParams(params)
        def formScan = new FormScan(params)

        assert formScan.save() != null

        // test invalid parameters in update
        params.id = formScan.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/formScan/edit"
        assert model.formScanInstance != null

        formScan.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/formScan/show/$formScan.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        formScan.clearErrors()

        populateValidParams(params)
        params.id = formScan.id
        params.version = -1
        controller.update()

        assert view == "/formScan/edit"
        assert model.formScanInstance != null
        assert model.formScanInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/formScan/list'

        response.reset()

        populateValidParams(params)
        def formScan = new FormScan(params)

        assert formScan.save() != null
        assert FormScan.count() == 1

        params.id = formScan.id

        controller.delete()

        assert FormScan.count() == 0
        assert FormScan.get(formScan.id) == null
        assert response.redirectedUrl == '/formScan/list'
    }
}
