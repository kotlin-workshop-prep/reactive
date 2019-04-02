package examples

import domain.Department
import domain.Employee
import org.junit.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class Part03MergeTest {

    private val part03Merge = Part03Merge()

    @Test
    fun `combine departments from two streams into an organization X`() {
        val organization = part03Merge.createOrganization(firstStream(), secondStream())

        StepVerifier.create(organization)
                .expectNextMatches{ it.id == 42 && it.name == "X" && it.employees.size == 4 && it.departments.size == 2 }
                .expectComplete()
    }

    fun firstStream(): Flux<Department> = Flux.just(Department(1, "A", 42, mutableListOf(
            Employee(1, "Employee1", 1000, 42, 1),
            Employee(2, "Employee2", 2000, 42, 1)
    )))

    fun secondStream(): Flux<Department> = Flux.just(Department(2, "B", 42, mutableListOf(
            Employee(3, "Employee3", 1000, 42, 2),
            Employee(4, "Employee4", 2000, 42, 2)
    )))


}