package co.neoris.service_bank.user_repository.account;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountDAORepository extends ReactiveCrudRepository<AccountDAO, Long> {
}
