package co.neoris.service_bank.api.client;

import co.neoris.service_bank.model.user.User;
import co.neoris.service_bank.usecase.createuser.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientHandler {
    private final CreateUserUseCase createUserUseCase;

    public Mono<ServerResponse> createClient(ServerRequest serverRequest) {
        // usecase.logic();
        return serverRequest.bodyToMono(ClientRequest.class)
                .map(request ->
                        new User(request.getCompleteName(), request.getGender(), request.getBirthDate(),
                                request.getIdentification(), request.getAddress(), request.getPhoneNumber(),
                                request.getPassword()))
                .flatMap(this.createUserUseCase::createUser)
                .flatMap(user -> ServerResponse.ok().bodyValue(user));
    }

    public Mono<ServerResponse> listenGETUseCase(ServerRequest serverRequest) {
        // usecase.logic();
        return ServerResponse.ok().bodyValue("");
    }

    public Mono<ServerResponse> listenGETOtherUseCase(ServerRequest serverRequest) {
        // useCase2.logic();
        return ServerResponse.ok().bodyValue("");
    }
}
