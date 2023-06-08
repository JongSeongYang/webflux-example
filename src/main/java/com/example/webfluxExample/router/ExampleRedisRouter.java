package com.example.webfluxExample.router;

import com.example.webfluxExample.handler.ExampleRedisHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class ExampleRedisRouter {

    private final ExampleRedisHandler exampleRedisHandler;

    @Bean
    public RouterFunction<ServerResponse> basicRoute() {
        return RouterFunctions.route()
                .GET("/reactive-list", serverRequest -> ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(exampleRedisHandler.findReactorList(), String.class))
                .GET("/normal-list", serverRequest -> ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                        .body(exampleRedisHandler.findNormalList(), String.class))
                .GET("/load", serverRequest -> { exampleRedisHandler.loadData(); return ServerResponse.ok()
                        .body(BodyInserters.fromValue("Load Data Completed")); })
                .build();
    }
}
