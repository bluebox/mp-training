package com.example.model;

import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my.app.users")
@PropertySource("classpath:application.properties")
public class LoginDetails {
//	@Value("${my.app.users.name}")
//	String val;
	HashMap<String,String> l;
	public HashMap<String, String> getData() {
		return l;
	}
	public void setData(HashMap<String, String> l) {
		this.l=l;
	}
}
