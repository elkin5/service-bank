package co.neoris.service_bank.model.person.gateways;

import co.neoris.service_bank.model.person.Person;
import reactor.core.publisher.Mono;

public interface PersonRepository {
    Mono<Person> createUser(Person person);
}
