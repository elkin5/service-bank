package co.neoris.service_bank.user_repository.transaction;

import co.neoris.service_bank.model.transaction.Transaction;
import co.neoris.service_bank.model.transaction.TransactionsReport;
import co.neoris.service_bank.model.transaction.gateways.TransactionRepository;
import co.neoris.service_bank.user_repository.transaction.report.TransactionReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryAdapter implements TransactionRepository {
    private final TransactionDAORepository transactionDaoRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionReportMapper transactionReportMapper;

    @Override
    public Mono<Transaction> createTransaction(Transaction transaction) {
        TransactionDAO transactionDAO = this.transactionMapper.toDAO(transaction);
        transactionDAO.setAccountId(transaction.getAccount().getId());
        return this.transactionDaoRepository.save(transactionDAO)
                .map(this.transactionMapper::toModel);
    }

    @Override
    public Flux<TransactionsReport> findReportByClientNameAndDate(String name, LocalDate date) {
        return this.transactionDaoRepository.findReportByClientNameAndDate(name, date)
                .map(this.transactionReportMapper::toModel);
    }
}
