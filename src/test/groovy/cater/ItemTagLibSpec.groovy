package cater

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll
import workshop.Item

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(cater.ItemTagLib)
class ItemTagLibSpec extends Specification {

    @Unroll
    void "Test taglib"() {
        given:
        def item = new Item(name: 'Name', outOfStock: outOfStock)

        expect:
        applyTemplate('<i:orderItem item="${item}"/>', [item: item]) == expectedText

        where:
        outOfStock || expectedText
        false      || '<a href="/order/create?item.id=&amount=1" class="btn btn-success">Order</a>'
        true       || '<span class="badge">Out of Stock</span>'
    }
}
