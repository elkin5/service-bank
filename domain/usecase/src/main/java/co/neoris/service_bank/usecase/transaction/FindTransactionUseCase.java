package co.neoris.service_bank.usecase.transaction;

import co.neoris.service_bank.model.transaction.Transaction;
import co.neoris.service_bank.model.transaction.TransactionsReport;
import co.neoris.service_bank.model.transaction.gateways.TransactionRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@RequiredArgsConstructor
public class FindTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public Flux<TransactionsReport> findReportByClientNameAndDate(String name, LocalDate date) {
        name = name.toLowerCase() + "%";
        return transactionRepository.findReportByClientNameAndDate(name, date);
    }
}
