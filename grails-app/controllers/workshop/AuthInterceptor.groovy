package workshop

import groovy.transform.CompileStatic

@CompileStatic
class AuthInterceptor {
    AuthInterceptor() {
        matchAll()
        .excludes(controller: 'login')
        .excludes(controller: 'owner', action: 'create')
        .excludes(controller: 'owner', action: 'save')
        .excludes(uri: '/')
    }

    boolean before() {
        if(!session.getAt('ownerId')) {
            redirect(controller: 'login')
            return false
        }
        return true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
