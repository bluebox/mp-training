package com.library.app.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfig {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder restTemplateBuilder =
                new RestTemplateBuilder();
        return restTemplateBuilder.basicAuthentication
                ("Jai", "123").build();
    }
    

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .filter(ExchangeFilterFunctions.basicAuthentication("Eren", "Attack"))
                .build();
    }
}
