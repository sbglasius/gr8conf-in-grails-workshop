package workshop

class LoginController {

    def index() {
        [ownerAuth: new OwnerAuth()]
    }

    def auth(OwnerAuth ownerAuth) {
        if(ownerAuth.hasErrors()) {
            render view:"index", model: [ownerAuth: ownerAuth]
            return
        }

        Owner owner = Owner.findByEmailAndPassword(ownerAuth?.email, ownerAuth?.password)
        if(!owner) {
            flash.message = message(code: "authentication.error", default: "Nobody found with email and password.")
            render view:"index", model: [ownerAuth: ownerAuth]
            return
        }

        session["ownerId"] = owner.id
        redirect controller: "order", action: "create", params: ["owner.id": owner.id, deliveryLocation: owner.defaultLocation, amount: 1]
        return
    }

}
