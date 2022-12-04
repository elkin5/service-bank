package co.neoris.service_bank.api.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class ClientRouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(ClientHandler clientHandler) {
        return route(GET("/api/clientes"), clientHandler::listenGETUseCase)
                .andRoute(POST("/api/clientes"), clientHandler::createClient)
                .and(route(PUT("/api/clientes"), clientHandler::listenGETOtherUseCase));
    }
}
