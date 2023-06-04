package com.example.webfluxExample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.webfluxExample"))
                .paths(PathSelectors.any())
                .build()
                .genericModelSubstitutes(Mono.class, Flux.class);
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumeContentTypes = new HashSet<>();
        consumeContentTypes.add("application/json;charset=UTF-8");
        consumeContentTypes.add("application/x-www-form-urlencoded");
        return consumeContentTypes;
    }

    private Set<String> getProduceContentTypes() {
        Set<String> produceContentTypes = new HashSet<>();
        produceContentTypes.add("application/json;charset=UTF-8");
        return produceContentTypes;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("webfluxExample API")
                .description("Description of API")
                .version("0.0.1")
                .termsOfServiceUrl("Terms of service")
                .contact(new Contact("webfluxExample", "", ""))
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .extensions(Collections.emptyList())
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
