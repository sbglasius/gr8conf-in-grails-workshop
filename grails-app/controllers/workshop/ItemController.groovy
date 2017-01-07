package workshop

class ItemController {

    static scaffold = Item

    def order() {
        [items: Item.list()]
    }

}
