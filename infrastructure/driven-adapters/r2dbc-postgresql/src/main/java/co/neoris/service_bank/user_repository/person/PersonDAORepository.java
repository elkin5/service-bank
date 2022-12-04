package co.neoris.service_bank.user_repository.person;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PersonDAORepository extends ReactiveCrudRepository<PersonDAO, Long> {
    Mono<PersonDAO> findByIdentification(Long identification);
}
