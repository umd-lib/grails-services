package edu.umd.lib.grails.services



import org.junit.*
import grails.test.mixin.*

@TestFor(AutoNumberController)
@Mock(AutoNumber)
class AutoNumberControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/autoNumber/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.autoNumberInstanceList.size() == 0
        assert model.autoNumberInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.autoNumberInstance != null
    }

    void testSave() {
        controller.save()

        assert model.autoNumberInstance != null
        assert view == '/autoNumber/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/autoNumber/show/1'
        assert controller.flash.message != null
        assert AutoNumber.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/autoNumber/list'

        populateValidParams(params)
        def autoNumber = new AutoNumber(params)

        assert autoNumber.save() != null

        params.id = autoNumber.id

        def model = controller.show()

        assert model.autoNumberInstance == autoNumber
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/autoNumber/list'

        populateValidParams(params)
        def autoNumber = new AutoNumber(params)

        assert autoNumber.save() != null

        params.id = autoNumber.id

        def model = controller.edit()

        assert model.autoNumberInstance == autoNumber
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/autoNumber/list'

        response.reset()

        populateValidParams(params)
        def autoNumber = new AutoNumber(params)

        assert autoNumber.save() != null

        // test invalid parameters in update
        params.id = autoNumber.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/autoNumber/edit"
        assert model.autoNumberInstance != null

        autoNumber.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/autoNumber/show/$autoNumber.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        autoNumber.clearErrors()

        populateValidParams(params)
        params.id = autoNumber.id
        params.version = -1
        controller.update()

        assert view == "/autoNumber/edit"
        assert model.autoNumberInstance != null
        assert model.autoNumberInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/autoNumber/list'

        response.reset()

        populateValidParams(params)
        def autoNumber = new AutoNumber(params)

        assert autoNumber.save() != null
        assert AutoNumber.count() == 1

        params.id = autoNumber.id

        controller.delete()

        assert AutoNumber.count() == 0
        assert AutoNumber.get(autoNumber.id) == null
        assert response.redirectedUrl == '/autoNumber/list'
    }
}
