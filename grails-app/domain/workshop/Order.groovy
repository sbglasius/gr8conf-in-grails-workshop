package workshop

class Order {
    Date deliveryDate
    String deliveryLocation
    int amount
    Item item

    Date dateCreated
    Date lastUpdated

    static constraints = {
        deliveryLocation nullable: false
        amount min: 1
        item nullable: false
        lastUpdated nullable: true
    }

    static mapping = {
        table name: 'ITEM_ORDER'
    }
}