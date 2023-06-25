package com.example.webfluxExample.handler;

import com.example.webfluxExample.dto.ExampleDto;
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
public class ExampleHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ok().contentType(MediaType.TEXT_PLAIN)
                .body(fromValue("Hello " + request.pathVariable("name")));
    }

    public Mono<ServerResponse> detail(ServerRequest request) {
        Mono<MultiValueMap<String, String>> formData = request.formData();
        return formData.flatMap(d->{
            Map<String, String> dataMap = d.toSingleValueMap();
            String name = dataMap.getOrDefault("name", "null");
            String age = dataMap.getOrDefault("age", "null");

            ExampleDto.ExampleResponse exampleResponse = ExampleDto.ExampleResponse.builder()
                    .name(name)
                    .age(Integer.parseInt(age))
                    .build();
            return ok().contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(exampleResponse);
        });
    }
}
