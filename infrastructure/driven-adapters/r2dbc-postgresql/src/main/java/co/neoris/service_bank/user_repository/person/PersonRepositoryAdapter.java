package co.neoris.service_bank.user_repository.person;

import co.neoris.service_bank.model.exception.InvalidIdentificationException;
import co.neoris.service_bank.model.exception.PersonNotFoundException;
import co.neoris.service_bank.model.person.Person;
import co.neoris.service_bank.model.person.gateways.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryAdapter implements PersonRepository {
    private final PersonDAORepository personDAORepository;
    private final PersonMapper personMapper;

    @Override
    public Mono<Person> createPerson(Person person) {
        return this.personDAORepository.save(this.personMapper.toDAO(person))
                .map(this.personMapper::toModel)
                .onErrorMap(error -> {
                    if (error instanceof DataIntegrityViolationException) {
                        throw new InvalidIdentificationException("Ya existe un cliente con este numero de identificación");
                    }

                    return error;
                });
    }

    @Override
    public Mono<Person> findPersonByIdentification(Long identification) {
        return this.personDAORepository.findByIdentification(identification)
                .map(this.personMapper::toModel)
                .switchIfEmpty(Mono.error(new PersonNotFoundException("No existe un cliente con este numero")));
    }

    @Override
    public Mono<Void> deletePersonById(Long identifier) {
        return this.personDAORepository.deleteById(identifier);
    }

    @Override
    public Mono<Person> findPersonById(Long identifier) {
        return personDAORepository.findById(identifier)
                .map(this.personMapper::toModel);
    }
}
