package co.neoris.service_bank.api.client;

import org.springframework.boot.context.properties.bind.Name;
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
public class ClientRouterRest {
    private static final String BASE_URL = "/api/clientes";

    @Bean("ClientRouterRest")
    public RouterFunction<ServerResponse> routerFunction(ClientHandler clientHandler) {
        return route(GET(BASE_URL), clientHandler::findByIdentification)
                .andRoute(POST(BASE_URL), clientHandler::createClient)
                .and(route(PUT(BASE_URL), clientHandler::updateClient))
                .and(route(DELETE(BASE_URL), clientHandler::deleteClient));
    }
}
