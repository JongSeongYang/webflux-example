package com.example.webfluxExample.router;

import com.example.webfluxExample.config.RouterFilterConfig;
import com.example.webfluxExample.handler.ExampleHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ExampleRouter {

    private final ExampleHandler exampleHandler;
    private final RouterFilterConfig routerFilterConfig;

    @Bean
    public RouterFunction<ServerResponse> helloRoute() {
        return RouterFunctions.route()
                .GET("/hello/{name}",accept(MediaType.TEXT_PLAIN),exampleHandler::hello)
                .build();
//                .route(RequestPredicates.GET("/hello/{name}")
//                        .and(accept(MediaType.TEXT_PLAIN)), exampleHandler::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> detailRoute() {
        return RouterFunctions
                .route(RequestPredicates.POST("/detail")
                        .and(accept(MediaType.TEXT_PLAIN)), exampleHandler::detail);
    }

    @Bean
    public RouterFunction<ServerResponse> detailRouteFilter() {
        return RouterFunctions.route()
                .POST("/detail/filter",accept(MediaType.APPLICATION_JSON),exampleHandler::detail)
//                .route(RequestPredicates.POST("/detail")
//                        .and(accept(MediaType.TEXT_PLAIN)), exampleHandler::detail)
                .filter(routerFilterConfig::routerFilter).build();
    }

    @Bean
    public RouterFunction<ServerResponse> detailCoRoute() {
        return RouterFunctions.route()
                .POST("/detail/co",accept(MediaType.APPLICATION_JSON),exampleHandler::detail)
//                .route(RequestPredicates.POST("/detail")
//                        .and(accept(MediaType.TEXT_PLAIN)), exampleHandler::detail)
                .filter(routerFilterConfig::routerFilter).build();
    }
}
