package co.neoris.service_bank.user_repository.transaction;

import co.neoris.service_bank.user_repository.transaction.report.TransactionReportDAO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface TransactionDAORepository extends ReactiveCrudRepository<TransactionDAO, Long> {

    @Query("SELECT tb.created_at, p.complete_name, ab.account_number, " +
            "ab.account_type, ab.initial_value, ab.state, tb.value, ab.balance " +
            "FROM servicebank.transaction_banking tb " +
            "INNER JOIN servicebank.account_banking ab ON tb.account_id = ab.id " +
            "INNER JOIN servicebank.person p ON ab.person_id = p.id " +
            "WHERE lower(p.complete_name) LIKE :name " +
            "AND to_date(tb.created_at::text, 'YYYY-MM-DD'::text) = :date")
    Flux<TransactionReportDAO> findReportByClientNameAndDate(String name, LocalDate date);
}
