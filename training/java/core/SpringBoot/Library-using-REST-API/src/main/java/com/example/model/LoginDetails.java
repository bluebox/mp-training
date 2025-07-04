package com.example.model;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my.app.users")
@PropertySource("classpath:application.properties")
@Profile("Ex1")
public class LoginDetails {
	@Value("${my.app.val}")
	String val;
	HashMap<String,String> l;
	public String getData() {
		return val;
	}
	public void setData(HashMap<String, String> l) {
		this.l=l;
	}
}
