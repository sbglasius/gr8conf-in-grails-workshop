package workshop

class LogoutController {

    def index() {
        session.invalidate()
        redirect(uri:'/')
    }


}
