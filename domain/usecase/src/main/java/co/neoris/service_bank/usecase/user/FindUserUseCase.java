package co.neoris.service_bank.usecase.user;

import co.neoris.service_bank.model.person.gateways.PersonRepository;
import co.neoris.service_bank.model.user.User;
import co.neoris.service_bank.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class FindUserUseCase {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    public Mono<User> findUserByIdentification(Long identification) {
        return this.personRepository.findPersonByIdentification(identification)
                .flatMap(person -> userRepository.findByPersonId(person.getPersonId())
                        .map(user -> {
                            user.setPerson(person);
                            return user;
                        }));
    }
}
