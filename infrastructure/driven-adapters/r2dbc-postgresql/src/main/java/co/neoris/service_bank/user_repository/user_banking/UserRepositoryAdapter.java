package co.neoris.service_bank.user_repository.user_banking;

import co.neoris.service_bank.model.user.User;
import co.neoris.service_bank.model.user.gateways.UserRepository;
import org.mapstruct.Mapper;
import reactor.core.publisher.Mono;

public class UserRepositoryAdapter implements UserRepository {
    @Override
    public Mono<User> createUser(User user) {
        return null;
    }
}
