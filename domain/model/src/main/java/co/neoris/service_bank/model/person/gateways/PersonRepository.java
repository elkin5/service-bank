package co.neoris.service_bank.model.person.gateways;

import co.neoris.service_bank.model.person.Person;
import reactor.core.publisher.Mono;

public interface PersonRepository {
    Mono<Person> createPerson(Person person);

    Mono<Person> findPersonByIdentification(Long identification);

    Mono<Void> deletePersonById(Long identifier);

    Mono<Person> findPersonById(Long identifier);
}
