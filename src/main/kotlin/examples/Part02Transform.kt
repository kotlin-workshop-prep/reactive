package examples

import domain.Department
import domain.Employee
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class Part02Transform {
    fun capitalizedNames(employees: Flux<Employee>): Flux<String> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return employees.map { it.name.toUpperCase() }
    }

    fun totalSalary(department: Mono<Department>): Mono<Int> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return department.map { it.employees.sumBy { e -> e.salary } }
    }

}
