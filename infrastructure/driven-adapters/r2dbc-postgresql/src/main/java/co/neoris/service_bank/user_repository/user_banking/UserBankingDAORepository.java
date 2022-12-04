package co.neoris.service_bank.user_repository.user_banking;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserBankingDAORepository extends ReactiveCrudRepository<UserBankingDAO, Long> {
}
