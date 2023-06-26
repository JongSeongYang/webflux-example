package com.example.webfluxExample.handler;

import com.example.webfluxExample.dto.ExampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@Slf4j
public class ExampleHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ok().contentType(MediaType.TEXT_PLAIN)
                .body(fromValue("Hello " + request.pathVariable("name")));
    }

    public Mono<ServerResponse> detail(ServerRequest request) {
        Mono<ExampleDto.ExampleResponse> responseMono = request.bodyToMono(ExampleDto.ExampleResponse.class);
        return responseMono.flatMap(r -> ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(r));
//        Mono<MultiValueMap<String, String>> formData = request.formData();
//        return formData.flatMap(d->{
//            Map<String, String> dataMap = d.toSingleValueMap();
//            String name = dataMap.getOrDefault("name", "null");
//            String age = dataMap.getOrDefault("age", "null");
//
//            log.info("name = {}",name);
//            log.info("age = {}",age);
//
//            ExampleDto.ExampleResponse exampleResponse = ExampleDto.ExampleResponse.builder()
//                    .name(name+" res")
//                    .age(Integer.parseInt(age)-1)
//                    .build();
//            return ok().contentType(MediaType.APPLICATION_JSON)
//                    .bodyValue(exampleResponse);
//        });
    }
}
