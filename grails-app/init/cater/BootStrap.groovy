package cater

import workshop.Item

class BootStrap {

    def init = { servletContext ->
        ['Espresso': 100, 'Latte': 100, 'Cappuccino': 100, 'Water': 50, 'Tea': 100, 'Snacks': 150, 'Chips': 150, 'Notepad': 0].each { name, price ->
            new Item(name: name, price: price).save(failOnError: true)
        }
    }
    def destroy = {
    }
}
