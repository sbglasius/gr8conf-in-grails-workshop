package workshop

import grails.transaction.Transactional

@Transactional
class OrderService {
    def messageSource

    def save(Order order) {
        order.save()
    }
}
