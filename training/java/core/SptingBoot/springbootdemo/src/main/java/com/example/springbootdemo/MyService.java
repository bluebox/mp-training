package com.example.springbootdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyService implements EnvironmentAware {

	@Value("${myapp.name}")
	private String appName;

	@Value("${myapp.timeout:30}")
	private int timeout; 
	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public void printProperties() {
		System.out.println("App Name (using @Value): " + appName);
		System.out.println("Timeout (using @Value): " + timeout);

		String envAppName = environment.getProperty("myapp.name");
		int envTimeout = Integer.parseInt(environment.getProperty("myapp.timeout", "30"));
		System.out.println("App Name (using Environment): " + envAppName);
		System.out.println("Timeout (using Environment): " + envTimeout);

	}
}
