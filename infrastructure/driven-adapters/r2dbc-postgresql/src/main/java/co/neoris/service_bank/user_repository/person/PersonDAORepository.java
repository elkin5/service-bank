package co.neoris.service_bank.user_repository.person;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonDAORepository extends ReactiveCrudRepository<PersonDAO, Long> {

}
