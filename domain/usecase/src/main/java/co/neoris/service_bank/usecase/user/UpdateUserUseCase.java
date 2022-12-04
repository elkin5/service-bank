package co.neoris.service_bank.usecase.user;

import co.neoris.service_bank.model.person.gateways.PersonRepository;
import co.neoris.service_bank.model.user.User;
import co.neoris.service_bank.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final CreateUserUseCase createUserUseCase;

    public Mono<User> updateUser(User user) {
        return this.personRepository.findPersonByIdentification(user.getIdentification())
                .flatMap(person -> userRepository.findByPersonId(person.getPersonId())
                        .map(savedUser -> {
                            savedUser.setPerson(person);
                            savedUser.setCompleteName(user.getCompleteName());
                            savedUser.setGender(user.getGender());
                            savedUser.setBirthDate(user.getBirthDate());
                            savedUser.setAddress(user.getAddress());
                            savedUser.setPhoneNumber(user.getPhoneNumber());
                            savedUser.setPassword(user.getPassword());
                            return savedUser;
                        })
                        .flatMap(this.createUserUseCase::createUser)
                );
    }
}
