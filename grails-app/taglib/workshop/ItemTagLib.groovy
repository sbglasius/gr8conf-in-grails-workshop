package cater

import workshop.Item

class ItemTagLib {
    static namespace="i"
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [orderItem: [taglib:'raw']]

    def orderItem = { attrs ->
        Item item = attrs.item

        if(!item) {
            throwTagError("Tag orderItem misses item attribute")
        }

        if(item.outOfStock) {
            out << """<span class="badge">Out of Stock</span>"""
        } else {
            out << g.link(controller: 'order', action: 'create', params: ['item.id': item.id, amount: 1], class: 'btn btn-success', 'Order')
        }
    }
}
