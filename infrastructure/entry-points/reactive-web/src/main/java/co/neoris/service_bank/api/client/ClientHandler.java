package co.neoris.service_bank.api.client;

import co.neoris.service_bank.model.exception.ValidationDataException;
import co.neoris.service_bank.model.user.User;
import co.neoris.service_bank.usecase.user.CreateUserUseCase;
import co.neoris.service_bank.usecase.user.DeleteUserUseCase;
import co.neoris.service_bank.usecase.user.FindUserUseCase;
import co.neoris.service_bank.usecase.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientHandler {
    private final CreateUserUseCase createUserUseCase;
    private final FindUserUseCase findUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public Mono<ServerResponse> createClient(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ClientRequest.class)
                .map(this::buildUser)
                .flatMap(this.createUserUseCase::createUser)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    throwable.printStackTrace();
                    return ServerResponse.badRequest().bodyValue(throwable.getMessage());
                });
    }

    public Mono<ServerResponse> findByIdentification(ServerRequest serverRequest) {
        long identification;
        try {
            identification = Long.parseLong(serverRequest.queryParam("identification").orElse("0"));
        } catch (NumberFormatException nfe) {
            log.error(nfe.getMessage());
            nfe.printStackTrace();
            throw new ValidationDataException("Identificación en formato invalido");
        }

        return this.findUserUseCase.findUserByIdentification(identification)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    throwable.printStackTrace();
                    return ServerResponse.badRequest().bodyValue(throwable.getMessage());
                });
    }

    public Mono<ServerResponse> updateClient(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ClientRequest.class)
                .map(this::buildUser)
                .flatMap(this.updateUserUseCase::updateUser)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    throwable.printStackTrace();
                    return ServerResponse.badRequest().bodyValue(throwable.getMessage());
                });
    }

    public Mono<ServerResponse> deleteClient(ServerRequest serverRequest) {
        long identification;
        try {
            identification = Long.parseLong(serverRequest.queryParam("identification").orElse("0"));
        } catch (NumberFormatException nfe) {
            log.error(nfe.getMessage());
            nfe.printStackTrace();
            throw new ValidationDataException("Identificación en formato invalido");
        }

        return this.deleteUserUseCase.deleteUser(identification)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    throwable.printStackTrace();
                    return ServerResponse.badRequest().bodyValue(throwable.getMessage());
                });
    }

    private User buildUser(ClientRequest request) {
        return new User(request.getCompleteName(), request.getGender(), request.getBirthDate(),
                request.getIdentification(), request.getAddress(), request.getPhoneNumber(),
                request.getPassword());
    }
}
