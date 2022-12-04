package co.neoris.service_bank.user_repository.account;

import co.neoris.service_bank.model.account.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "personId", ignore = true)
    AccountDAO toDAO(Account account);

    @Mapping(target = "person", ignore = true)
    Account toModel(AccountDAO accountDAO);
}
