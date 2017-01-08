package workshop

import grails.test.mixin.*
import spock.lang.*

@TestFor(OrderController)
@Mock([Order, Item, Owner])
class OrderControllerSpec extends Specification {

    def doWithSpring = {
        orderService(OrderService)
    }
    
    def populateValidParams(params) {
        assert params != null
params["owner.id"] = new Owner(name: "John Doe", defaultLocation: "Desk007").save(flush: true, validate: false).id
        params["deliveryDate"] = 'date.struct'
        params["deliveryDate_day"] = '8'
        params["deliveryDate_month"] = '1'
        params["deliveryDate_year"] = '2017'
        params["deliveryLocation"] = "Default Location"
        params["amount"] = 1
        params["item.id"] = new Item(name: "Latte", price: 100).save(flush: true, validate: false).id
    }


    void "Test the index action returns the correct model"() {

        when: "The index action is executed"
        controller.index()

        then: "The model is correct"
        !model.orderList
        model.orderCount == 0
    }

    void "Test the create action returns the correct model"() {
        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.order != null
    }

    void "should create an order without any deliveryLocation"() {
        setup:
        populateValidParams(params)
        params["owner.id"] = null

        when: "The create action is executed"
        controller.create()

        then: "The model is correctly created"
        model.order.deliveryLocation == "Default Location"
    }

    void "should create an order with defaultLoation from Owner"() {
        setup:
        populateValidParams(params)

        when:
        controller.create()

        then:
        model.order.deliveryLocation == "Desk007"
    }

    void "Test the save action correctly persists an instance"() {

        when: "The save action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def order = new Order()
        order.validate()
        controller.save(order)

        then: "The create view is rendered again with the correct model"
        model.order != null
        view == 'create'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        order = new Order(params)

        controller.save(order)

        then: "A redirect is issued to the show action"
        response.redirectedUrl == '/order/show/1'
        controller.flash.message != null
        Order.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def order = new Order(params)
        controller.show(order)

        then: "A model is populated containing the domain instance"
        model.order == order
    }

    void "Test that the edit action returns the correct model"() {
        when: "The edit action is executed with a null domain"
        controller.edit(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the edit action"
        populateValidParams(params)
        def order = new Order(params)
        controller.edit(order)

        then: "A model is populated containing the domain instance"
        model.order == order
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when: "Update is called for a domain instance that doesn't exist"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'PUT'
        controller.update(null)

        then: "A 404 error is returned"
        response.redirectedUrl == '/order/index'
        flash.message != null

        when: "An invalid domain instance is passed to the update action"
        response.reset()
        def order = new Order()
        order.validate()
        controller.update(order)

        then: "The edit view is rendered again with the invalid instance"
        view == 'edit'
        model.order == order

        when: "A valid domain instance is passed to the update action"
        response.reset()
        populateValidParams(params)
        order = new Order(params).save(flush: true)
        controller.update(order)

        then: "A redirect is issued to the show action"
        order != null
        response.redirectedUrl == "/order/show/$order.id"
        flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when: "The delete action is called for a null instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'DELETE'
        controller.delete(null)

        then: "A 404 is returned"
        response.redirectedUrl == '/order/index'
        flash.message != null

        when: "A domain instance is created"
        response.reset()
        populateValidParams(params)
        def order = new Order(params).save(flush: true)

        then: "It exists"
        Order.count() == 1

        when: "The domain instance is passed to the delete action"
        controller.delete(order)

        then: "The instance is deleted"
        Order.count() == 0
        response.redirectedUrl == '/order/index'
        flash.message != null
    }
}
