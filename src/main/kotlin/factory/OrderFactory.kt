package factory

import domain.Customer
import domain.Order

class OrderFactory {
    fun order(id: Int): Order {
        return Order(id, listOf(), Customer("Alice"))
    }
}