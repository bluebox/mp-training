package com.example.springbootexample.demospringboot;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import lombok.Data;

@Component
@SessionScope
@Data
public class Person {

	private String name="shankar";
	private int id;
}
