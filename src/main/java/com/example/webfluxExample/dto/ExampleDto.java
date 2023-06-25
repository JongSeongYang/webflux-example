package com.example.webfluxExample.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ExampleDto {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ExampleResponse {
        private String name;
        private Integer age;
    }
}
