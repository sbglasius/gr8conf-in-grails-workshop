package workshop

class Owner {
    String name
    String defaultLocation

    static hasMany = [orders: Order]

    static constraints = {
        name nullable: false
        defaultLocation nullable: false
    }

    String toString() {
        name
    }
}