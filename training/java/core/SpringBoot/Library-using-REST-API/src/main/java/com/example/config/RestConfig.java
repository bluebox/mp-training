package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.model.LoginDetails;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
//@Profile("Ex1")
public class RestConfig {
	@Autowired
	Environment e;
	@Autowired
	LoginDetails l;
	@Bean
	public RestTemplate resttemplate(RestTemplateBuilder builder) {
		log.info(l.getData());
		log.info("Hi friends how are you zxcfdgdd                             dffs");
		return builder.basicAuthentication(e.getProperty("spring.username"), e.getProperty("spring.password")).build();
	}
	@Bean
	public WebClient web() {
		return WebClient.builder()
				.filter(ExchangeFilterFunctions.basicAuthentication(e.getProperty("username"), "43434")).build();
	}
}
