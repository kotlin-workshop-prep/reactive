package examples

import domain.Department
import domain.Employee
import domain.Organization
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part03Merge {
    fun createOrganization(firstStream: Flux<Department>, secondStream: Flux<Department>): Mono<Organization> {
        return firstStream.mergeWith(secondStream)
                .flatMapIterable { it.employees }
                .collectList()
                .map { Organization(42, "X", it) }
    }

    fun createEmployees(names: Flux<String>, salaries: Flux<Int>): Flux<Employee> {

        return Flux.zip(Flux.range(1, 100), names, salaries)
                .map { Employee(it.t1, it.t2, it.t3) }
    }

    fun createDepartment(names: Flux<String>, salaries: Flux<Int>): Mono<Department> {
        return createEmployees(names, salaries).collectList().map { Department(1, "Dept1", 42, it) }
    }
}
