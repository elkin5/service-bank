package co.neoris.service_bank.user_repository.user_banking;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserBankingDAORepository extends ReactiveCrudRepository<UserBankingDAO, Long> {
    Mono<UserBankingDAO> findByPersonId(Long personId);
}
