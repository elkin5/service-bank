package co.neoris.service_bank.api.account;

import co.neoris.service_bank.model.account.Account;
import co.neoris.service_bank.usecase.account.CreateAccountUseCase;
import co.neoris.service_bank.usecase.account.DeleteAccountUseCase;
import co.neoris.service_bank.usecase.account.FindAccountUseCase;
import co.neoris.service_bank.usecase.account.UpdateAccountUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountHandler {
    private final CreateAccountUseCase createAccountUseCase;
    private final FindAccountUseCase findAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    public Mono<ServerResponse> createAccount(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(AccountRequest.class)
                .map(request -> new Account(request.getNumber(), request.getType(), request.getInitialValue(),
                        request.getIdentification()))
                .flatMap(this.createAccountUseCase::createAccount)
                .flatMap(account -> ServerResponse.ok().bodyValue(account))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    throwable.printStackTrace();
                    return ServerResponse.badRequest().bodyValue(throwable.getMessage());
                });
    }

    public Mono<ServerResponse> findByIdentification(ServerRequest serverRequest) {
        return ServerResponse.badRequest().bodyValue("");
    }

    public Mono<ServerResponse> updateAccount(ServerRequest serverRequest) {
        return ServerResponse.badRequest().bodyValue("");
    }

    public Mono<ServerResponse> deleteAccount(ServerRequest serverRequest) {
        return ServerResponse.badRequest().bodyValue("");
    }
}
