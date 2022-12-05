package co.neoris.service_bank.usecase.transaction;

import co.neoris.service_bank.model.account.gateways.AccountRepository;
import co.neoris.service_bank.model.exception.AmountExceededException;
import co.neoris.service_bank.model.exception.ValidationDataException;
import co.neoris.service_bank.model.transaction.Transaction;
import co.neoris.service_bank.model.transaction.gateways.TransactionRepository;
import co.neoris.service_bank.usecase.account.FindAccountUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class CreateTransactionUseCase {
    private final TransactionRepository transactionRepository;
    private final FindAccountUseCase findAccountUseCase;
    private final AccountRepository accountRepository;

    public Mono<Transaction> createTransaction(Transaction transaction) {
        return this.findAccountUseCase.findAccountByNumber(transaction.getAccount().getNumber())
                .flatMap(account -> {
                    if (transaction.getValue().compareTo(account.getBalance()) > 0) {
                        return Mono.error(new AmountExceededException(
                                "Monto invalido: el monto a retirar debe ser menor al disponible"));
                    }
                    BigDecimal value = transaction.getType().equals(Transaction.TransactionType.DEPOSITO) ?
                            transaction.getValue() : transaction.getValue().negate();
                    transaction.setAccount(account);
                    transaction.setInitialValue(account.getBalance());
                    BigDecimal newBalance = account.getBalance().add(value);
                    transaction.setValue(value);
                    transaction.setEndingValue(newBalance);
                    account.setBalance(newBalance);

                    return this.accountRepository.createAccount(account)
                            .map(unused -> transaction);
                })
                .flatMap(newTransaction -> this.transactionRepository.createTransaction(newTransaction)
                        .map(saveTransaction -> {
                            newTransaction.setId(saveTransaction.getId());
                            return newTransaction;
                        }));
    }
}
