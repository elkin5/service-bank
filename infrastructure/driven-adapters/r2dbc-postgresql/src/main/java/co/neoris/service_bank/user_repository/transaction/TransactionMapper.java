package co.neoris.service_bank.user_repository.transaction;

import co.neoris.service_bank.model.transaction.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "accountId", ignore = true)
    TransactionDAO toDAO(Transaction account);

    @Mapping(target = "account", ignore = true)
    Transaction toModel(TransactionDAO accountDAO);
}
