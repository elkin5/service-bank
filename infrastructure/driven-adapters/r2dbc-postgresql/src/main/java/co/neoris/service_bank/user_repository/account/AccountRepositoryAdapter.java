package co.neoris.service_bank.user_repository.account;

import co.neoris.service_bank.model.account.Account;
import co.neoris.service_bank.model.account.gateways.AccountRepository;
import co.neoris.service_bank.model.exception.PersonNotFoundException;
import co.neoris.service_bank.model.person.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {
    private final AccountDAORepository accountDaoRepository;
    private final AccountMapper accountMapper;

    @Override
    public Mono<Account> createAccount(Account account) {
        AccountDAO accountDAO = this.accountMapper.toDAO(account);
        accountDAO.setPersonId(account.getPerson().getPersonId());
        return this.accountDaoRepository.save(accountDAO)
                .map(this.accountMapper::toModel);
    }

    @Override
    public Mono<Account> findAccountByNumber(Long accountNumber) {
        return this.accountDaoRepository.findByNumber(accountNumber)
                .map(accountDAO -> {
                    Account newAccount = this.accountMapper.toModel(accountDAO);
                    Person person = new Person();
                    person.setPersonId(accountDAO.getPersonId());
                    newAccount.setPerson(person);
                    return newAccount;
                })
                .switchIfEmpty(Mono.error(new PersonNotFoundException("No existe una cuenta con este numero")));
    }
}
