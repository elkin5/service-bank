package co.neoris.service_bank.usecase.account;

import co.neoris.service_bank.model.account.Account;
import co.neoris.service_bank.model.account.gateways.AccountRepository;
import co.neoris.service_bank.model.person.gateways.PersonRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    public Mono<Account> createAccount(Account account) {
        return this.personRepository.findPersonByIdentification(account.getPerson().getIdentification())
                .map(person -> {
                    account.setPerson(person);
                    return account;
                })
                .flatMap(newAccount -> this.accountRepository.createAccount(newAccount)
                        .map(saveAccount -> {
                            newAccount.setId(saveAccount.getId());
                            return newAccount;
                        }));
    }
}
