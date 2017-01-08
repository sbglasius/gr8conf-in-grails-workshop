package workshop

import grails.validation.Validateable

class OwnerAuth implements Validateable {
    String email
    String password

    static constraints = {
        email email: true
        password password: true,minSize: 8
    }
}

