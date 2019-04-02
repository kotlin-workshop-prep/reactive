package examples

import domain.Department
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
}
