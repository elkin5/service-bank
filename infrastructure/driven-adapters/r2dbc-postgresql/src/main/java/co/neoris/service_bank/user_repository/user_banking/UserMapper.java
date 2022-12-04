package co.neoris.service_bank.user_repository.user_banking;

import co.neoris.service_bank.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserBankingDAO toDAO(User user);

//    @Mapping(target = "id", ignore = true)
    User toModel(UserBankingDAO userBankingDAO);
}
