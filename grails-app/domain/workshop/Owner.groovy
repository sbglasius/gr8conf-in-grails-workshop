package workshop

class Owner {
    String name
    String defaultLocation

    static constraints = {
        name nullable: false
        defaultLocation nullable: false
    }
}