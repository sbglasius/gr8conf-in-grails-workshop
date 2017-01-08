package workshop

class Owner {
    String name
    String email
    String defaultLocation
    String password

    static hasMany = [orders: Order]

    static constraints = {
        name nullable: false
        defaultLocation nullable: false
    }

    String toString() {
        name
    }
}