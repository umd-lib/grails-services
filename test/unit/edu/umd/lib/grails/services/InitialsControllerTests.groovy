package edu.umd.lib.grails.services



import org.junit.*
import grails.test.mixin.*

@TestFor(InitialsController)
@Mock(Initials)
class InitialsControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      params["initialsName"] = 'TST'
    }

    void testIndex() {
        controller.index()
        assert "/initials/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.initialsInstanceList.size() == 0
        assert model.initialsInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.initialsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.initialsInstance != null
        assert view == '/initials/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/initials/show/1'
        assert controller.flash.message != null
        assert Initials.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/initials/list'


        populateValidParams(params)
        def initials = new Initials(params)

        assert initials.save() != null

        params.id = initials.id

        def model = controller.show()

        assert model.initialsInstance == initials
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/initials/list'


        populateValidParams(params)
        def initials = new Initials(params)

        assert initials.save() != null

        params.id = initials.id

        def model = controller.edit()

        assert model.initialsInstance == initials
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/initials/list'

        response.reset()


        populateValidParams(params)
        def initials = new Initials(params)

        assert initials.save() != null

        // test invalid parameters in update
        params.id = initials.id
        //TODO: add invalid values to params object
		params.initialsName = null

        controller.update()

        assert view == "/initials/edit"
        assert model.initialsInstance != null

        initials.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/initials/show/$initials.id"
        assert flash.message != null

        //test outdated version number
//        response.reset()
//        initials.clearErrors()
//
//        populateValidParams(params)
//        params.id = initials.id
//        params.version = -1
//        controller.update()
//
//        assert view == "/initials/edit"
//        assert model.initialsInstance != null
//        assert model.initialsInstance.errors.getFieldError('version')
//        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/initials/list'

        response.reset()

        populateValidParams(params)
        def initials = new Initials(params)

        assert initials.save() != null
        assert Initials.count() == 1

        params.id = initials.id

        controller.delete()

        assert Initials.count() == 0
        assert Initials.get(initials.id) == null
        assert response.redirectedUrl == '/initials/list'
    }
}
