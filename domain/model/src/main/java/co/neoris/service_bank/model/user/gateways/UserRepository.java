package co.neoris.service_bank.model.user.gateways;

import co.neoris.service_bank.model.person.Person;
import co.neoris.service_bank.model.user.User;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Mono<User> createUser(User user, Person person);
    Mono<User> findByPersonId(Long identifier);
    Mono<Void> deleteUserByUserId(Long identifier);
}
