package examples

import domain.Customer
import domain.Order
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part02Transform {
    fun capitalizedNames(customers: Flux<Customer>): Flux<String> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return customers.map { it.name.toUpperCase() }
    }

    fun totalForOrder(order: Mono<Order>): Mono<Double> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return order.map { it.lineItems.map { l -> l.quantity * l.product.price }.reduce{ x, y -> x + y} }
    }

}
