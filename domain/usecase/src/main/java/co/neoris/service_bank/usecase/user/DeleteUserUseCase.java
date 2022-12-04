package co.neoris.service_bank.usecase.user;

import co.neoris.service_bank.model.person.gateways.PersonRepository;
import co.neoris.service_bank.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final PersonRepository personRepository;
    private final UserRepository userRepository;

    public Mono<String> deleteUser(Long identification) {
        return this.personRepository.findPersonByIdentification(identification)
                .flatMap(person -> userRepository.findByPersonId(person.getPersonId())
                        .flatMap(user -> this.userRepository.deleteUserByUserId(user.getUserId())
                                .then(this.personRepository.deletePersonById(person.getPersonId()))
                        ))
                .thenReturn("Eliminado exitoso");
    }
}
