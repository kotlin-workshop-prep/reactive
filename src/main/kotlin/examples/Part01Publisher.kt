package examples

import domain.Employee
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part01Publisher {

    fun emptyEmployeeMono(): Mono<Employee> {
//        TODO("not implemented")
        return Mono.empty()
    }

    fun twoEmployees(): Flux<Employee> {
//        TODO("not implemented")
        return Flux.just(Employee(1, "Alice", 5000), Employee(2, "Bob", 4000))
    }

}