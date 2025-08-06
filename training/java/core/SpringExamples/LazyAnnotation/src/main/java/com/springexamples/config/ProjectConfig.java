package com.springexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.springexamples.beans.EagerMessage;
import com.springexamples.beans.Message;

@Configuration
public class ProjectConfig {
	
	@Bean
	//@Lazy
	Message message() {
		return new Message();
	}
	
	@Bean
	EagerMessage message2() {
		return new EagerMessage();
	}
}
