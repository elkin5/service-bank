package co.neoris.service_bank.model.account.gateways;

import co.neoris.service_bank.model.account.Account;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Mono<Account> createAccount(Account account);

    Mono<Account> findAccountByNumber(Long accountNumber);
}
