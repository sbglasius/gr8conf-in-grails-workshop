package workshop

class Order {
    Date deliveryDate
    String deliveryLocation
    int amount
    Item item
    OrderStatus status = OrderStatus.PENDING

    Date dateCreated
    Date lastUpdated

    static belongsTo = [owner: Owner]

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