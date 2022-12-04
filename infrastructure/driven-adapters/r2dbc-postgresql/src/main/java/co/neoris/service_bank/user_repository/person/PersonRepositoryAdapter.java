package co.neoris.service_bank.user_repository.person;

import co.neoris.service_bank.model.person.Person;
import co.neoris.service_bank.model.person.gateways.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryAdapter implements PersonRepository {
    private final PersonDAORepository personDAORepository;
    private final PersonMapper personMapper;

    @Override
    public Mono<Person> createUser(Person person) {
        return this.personDAORepository.save(this.personMapper.toDAO(person))
                .map(this.personMapper::toModel);
    }
}
