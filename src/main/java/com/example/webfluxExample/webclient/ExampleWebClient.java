package com.example.webfluxExample.webclient;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class ExampleWebClient {

    private WebClient client = WebClient.create("http://localhost:8081");

//    Mono<Object> result = client.get().uri("/hello/JongSeong")
//            .accept(MediaType.TEXT_PLAIN).exchangeToMono(res -> {
//                if (res.statusCode().equals(HttpStatus.OK)) {
//                    return res.bodyToMono(String.class);
//                }else {
//                    return res.createException().flatMap(Mono::error);
//                }
//            });
//
//    public String getResult() {
//        return ">> result = " + result.block();
//    }
}
