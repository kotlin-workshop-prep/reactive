package examples

import domain.Customer
import domain.LineItem
import domain.Order
import domain.Product
import org.junit.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class Part02TransformTest {
    val part02Transform = Part02Transform()

    @Test
    fun `should get capitalized list of names`() {

        val customers = Flux.just(Customer("Alice"), Customer("Bob"), Customer("Carol"))

        val names: Flux<String> = part02Transform.capitalizedNames(customers)

        StepVerifier.create(names)
                .expectNext("ALICE")
                .expectNext("BOB")
                .expectNext("CAROL")
                .verifyComplete()

    }

    @Test
    fun `should get total amount of order`() {
        val order = Mono.just(Order(1, listOf(
                LineItem(Product(1, "Apples", 80.0), 2),
                LineItem(Product(2, "Oranges", 100.0), 3),
                LineItem(Product(3, "Grapes", 120.0), 1)
        ), Customer("Alice")))

        val totalForOrder = part02Transform.totalForOrder(order)

        StepVerifier.create(totalForOrder)
                .expectNext(580.0)
                .verifyComplete()
    }
}
