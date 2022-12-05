package co.neoris.service_bank.api.transaction;

import co.neoris.service_bank.model.exception.ValidationDataException;
import co.neoris.service_bank.model.transaction.Transaction;
import co.neoris.service_bank.usecase.transaction.CreateTransactionUseCase;
import co.neoris.service_bank.usecase.transaction.DeleteTransactionUseCase;
import co.neoris.service_bank.usecase.transaction.FindTransactionUseCase;
import co.neoris.service_bank.usecase.transaction.UpdateTransactionUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionHandler {
    private final CreateTransactionUseCase createTransactionUseCase;
    private final FindTransactionUseCase findTransactionUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;

    public Mono<ServerResponse> createTransaction(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(TransactionRequest.class)
                .map(request -> new Transaction(request.getValue(), request.getType(), request.getAccountNumber()))
                .flatMap(this.createTransactionUseCase::createTransaction)
                .flatMap(transaction -> ServerResponse.ok().bodyValue(transaction))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    throwable.printStackTrace();
                    return ServerResponse.badRequest().bodyValue(throwable.getMessage());
                });
    }

    public Mono<ServerResponse> findReportByClientNameAndDate(ServerRequest serverRequest) {
        String clientName = serverRequest.queryParam("name").orElse("");
        String dateParam = serverRequest.queryParam("date").orElse("");
        LocalDate date = LocalDate.now();
        if (!dateParam.isEmpty()) {
            try {
                date = LocalDate.parse(dateParam, DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException ex) {
                throw new ValidationDataException("Fecha en formato invalido");
            }
        }

        return this.findTransactionUseCase.findReportByClientNameAndDate(clientName, date)
                .collectList()
                .flatMap(transactions -> ServerResponse.ok().bodyValue(transactions))
                .onErrorResume(throwable -> {
                    log.error(throwable.getMessage());
                    throwable.printStackTrace();
                    return ServerResponse.badRequest().bodyValue(throwable.getMessage());
                });
    }

    public Mono<ServerResponse> updateTransaction(ServerRequest serverRequest) {
        return ServerResponse.badRequest().bodyValue("");
    }

    public Mono<ServerResponse> deleteTransaction(ServerRequest serverRequest) {
        return ServerResponse.badRequest().bodyValue("");
    }
}
