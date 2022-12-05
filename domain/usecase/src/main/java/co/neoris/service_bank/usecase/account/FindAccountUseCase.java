package co.neoris.service_bank.usecase.account;

import co.neoris.service_bank.model.account.Account;
import co.neoris.service_bank.model.account.gateways.AccountRepository;
import co.neoris.service_bank.model.person.gateways.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindAccountUseCase {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    public Mono<Account> findAccountByNumber(Long accountNumber) {
        return this.accountRepository.findAccountByNumber(accountNumber)
                .flatMap(account -> personRepository.findPersonById(account.getPerson().getPersonId())
                        .map(person -> {
                            account.setPerson(person);
                            return account;
                        }));
    }
}
