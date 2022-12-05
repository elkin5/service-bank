package co.neoris.service_bank.model.transaction.gateways;

import co.neoris.service_bank.model.transaction.Transaction;
import co.neoris.service_bank.model.transaction.TransactionsReport;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TransactionRepository {
    Mono<Transaction> createTransaction(Transaction transaction);

    Flux<TransactionsReport> findReportByClientNameAndDate(String name, LocalDate date);
}
