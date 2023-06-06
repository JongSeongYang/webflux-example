package com.example.webfluxExample.router;

import com.example.webfluxExample.handler.ExampleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class ExampleRouter {

    private final ExampleHandler exampleHandler;

    @Bean
    public RouterFunction<ServerResponse> helloRoute() {
        return RouterFunctions
                .route(RequestPredicates.GET("/hello/{name}")
                        .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), exampleHandler::hello);
    }
}
