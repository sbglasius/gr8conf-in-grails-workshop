package workshop

class Item {
    String name
    BigDecimal price
    boolean outOfStock

    static constraints = {
        name nullable: false
        price min: 0.0, scale: 2
    }

    String toString() {
        "${name} (${price})"
    }
}
