package com.example.webfluxExample.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.EntityResponse;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class RouterFilterConfig {

    public Mono<ServerResponse> routerFilter(ServerRequest request, HandlerFunction<ServerResponse> handler) {
        return request.bodyToMono(String.class) // 1
                .doOnNext(body -> requestLogging(request, body)) // 2
                .flatMap(body -> handler.handle(ServerRequest.from(request).body(body).build())) // 3
                .doOnNext(response -> responseLogging(request, response)); // 4
    }

    private void requestLogging(ServerRequest request, String body) {
        log.info("Request : {} {} | {}", request.method(), request.path(), body);
    }

    private void responseLogging(ServerRequest request, ServerResponse response) {
        log.info("Response : {} {} | {}", request.method(), request.path(), ((EntityResponse<?>) response).entity());
    }
}
