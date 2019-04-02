package examples

import org.junit.Test
import reactor.test.StepVerifier

class Part01PublisherTest {

    private val part01Publisher = Part01Publisher()

    @Test
    fun `should return one employee in a Mono`() {
        val mono = part01Publisher.emptyEmployeeMono()
        StepVerifier.create(mono).verifyComplete()
    }

    @Test
    fun `should return two employees with ids 1 and 2 in a flux`() {
        val flux = part01Publisher.twoEmployees()
        StepVerifier.create(flux)
                .expectNextMatches {it.id == 1}
                .expectNextMatches {it.id == 2}
                .verifyComplete()
    }
}

