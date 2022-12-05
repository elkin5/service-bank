package co.neoris.service_bank.api.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TransactionRouterRest {
    private static final String BASE_URL = "/api/movimientos";

    @Bean(name = "TransactionRouterRest")
    public RouterFunction<ServerResponse> routerFunction(TransactionHandler transactionHandler) {
        return route(GET(BASE_URL), transactionHandler::findReportByClientNameAndDate)
                .andRoute(POST(BASE_URL), transactionHandler::createTransaction)
                .and(route(PUT(BASE_URL), transactionHandler::updateTransaction))
                .and(route(DELETE(BASE_URL), transactionHandler::deleteTransaction));
    }
}
