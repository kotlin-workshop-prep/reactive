package examples

import domain.Department
import domain.Employee
import org.junit.Test
import reactor.core.publisher.Flux
import reactor.test.StepVerifier

class Part03MergeTest {

    private val part03Merge = Part03Merge()

    @Test
    fun `merge two streams and give serial numbers for employees`() {
        val names = Flux.just("Alice", "Bob", "Carol")
        val salaries = Flux.just(5000, 4000, 6000)

        val employees = part03Merge.createEmployees(names, salaries)

        StepVerifier.create(employees)
                .expectNextMatches{ it.id == 1 && it.name == "Alice" && it.salary == 5000}
                .expectNextMatches{ it.id == 2 && it.name == "Bob" && it.salary == 4000}
                .expectNextMatches{ it.id == 3 && it.name == "Carol" && it.salary == 6000}
                .expectComplete()
    }

    @Test
    fun `merge two streams and give serial numbers for employees, and construct a department`() {
        val names = Flux.just("Alice", "Bob", "Carol")
        val salaries = Flux.just(5000, 4000, 6000)

        val department = part03Merge.createDepartment(names, salaries)

        StepVerifier.create(department)
                .expectNextMatches{ it.id == 1 && it.name == "Dept1" && it.organizationId == 42 && it.employees.size == 3}
                .expectComplete()
    }


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