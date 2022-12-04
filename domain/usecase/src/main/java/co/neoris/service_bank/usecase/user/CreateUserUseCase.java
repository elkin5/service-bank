package co.neoris.service_bank.usecase.user;

import co.neoris.service_bank.model.person.gateways.PersonRepository;
import co.neoris.service_bank.model.user.User;
import co.neoris.service_bank.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateUserUseCase {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    public Mono<User> createUser(User user) {
        return this.personRepository.createPerson(user)
                .flatMap(person -> this.userRepository.createUser(user, person)
                        .map(newUser -> {
                            user.setPersonId(person.getPersonId());
                            user.setUserId(newUser.getUserId());
                            return user;
                        })
                );
    }
}
