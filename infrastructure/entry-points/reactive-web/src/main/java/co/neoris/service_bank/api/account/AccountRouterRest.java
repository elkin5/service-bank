package co.neoris.service_bank.api.account;

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
public class AccountRouterRest {
    private static final String BASE_URL = "/api/cuentas";

    @Bean(name = "AccountRouterRest")
    public RouterFunction<ServerResponse> routerFunction(AccountHandler accountHandler) {
        return route(GET(BASE_URL), accountHandler::findByIdentification)
                .andRoute(POST(BASE_URL), accountHandler::createAccount)
                .and(route(PUT(BASE_URL), accountHandler::updateAccount))
                .and(route(DELETE(BASE_URL), accountHandler::deleteAccount));
    }
}
