package co.neoris.service_bank.user_repository.user_banking;

import co.neoris.service_bank.model.exception.SaveUserNotFoundException;
import co.neoris.service_bank.model.exception.UserNotFoundException;
import co.neoris.service_bank.model.person.Person;
import co.neoris.service_bank.model.user.User;
import co.neoris.service_bank.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserBankingDAORepository userBankingDAORepository;
    private final UserMapper userMapper;

    @Override
    public Mono<User> createUser(User user, Person person) {
        return this.userBankingDAORepository.save(this.userMapper.toDAO(user, person))
                .map(this.userMapper::toModel)
                .onErrorMap(throwable -> {
                    throw new SaveUserNotFoundException("No se pudo crear el usuario: " + throwable.getMessage());
                });
    }

    @Override
    public Mono<User> findByPersonId(Long identifier) {
        return this.userBankingDAORepository.findByPersonId(identifier)
                .map(this.userMapper::toModel)
                .switchIfEmpty(Mono.error(new UserNotFoundException("No existe un usuario para esta identificación")));
    }

    @Override
    public Mono<Void> deleteUserByUserId(Long identifier) {
        return this.userBankingDAORepository.deleteById(identifier);
    }
}
