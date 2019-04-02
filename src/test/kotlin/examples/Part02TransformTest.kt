package examples

import domain.*
import org.junit.Test
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

class Part02TransformTest {
    private val part02Transform = Part02Transform()

    @Test
    fun `should get capitalized list of names`() {

        val employees = Flux.just(Employee(1, "Alice", 5000),
                Employee(2, "Bob", 4000),
                Employee(3, "Carol", 6000))

        val names: Flux<String> = part02Transform.capitalizedNames(employees)

        StepVerifier.create(names)
                .expectNext("ALICE")
                .expectNext("BOB")
                .expectNext("CAROL")
                .verifyComplete()

    }

    @Test
    fun `should get total salary in a department`() {

        val department = Mono.just(Department(1, "Finance", 42, mutableListOf(Employee(1, "Alice", 5000),
                Employee(2, "Bob", 4000),
                Employee(3, "Carol", 6000))))

        val totalSalaryOfDepartment = part02Transform.totalSalary(department)

        StepVerifier.create(totalSalaryOfDepartment)
                .expectNext(15000)
                .verifyComplete()
    }
}
