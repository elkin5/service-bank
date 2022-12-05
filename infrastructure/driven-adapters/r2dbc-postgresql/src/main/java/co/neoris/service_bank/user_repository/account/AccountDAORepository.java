package co.neoris.service_bank.user_repository.account;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface AccountDAORepository extends ReactiveCrudRepository<AccountDAO, Long> {
    Mono<AccountDAO> findByNumber(Long number);
}
