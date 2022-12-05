package co.neoris.service_bank.user_repository.transaction.report;

import co.neoris.service_bank.model.transaction.TransactionsReport;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionReportMapper {
    TransactionReportDAO toDAO(TransactionsReport transactionsReport);

    TransactionsReport toModel(TransactionReportDAO transactionsReportDAO);
}
