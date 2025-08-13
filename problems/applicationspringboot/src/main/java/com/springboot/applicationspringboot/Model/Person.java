package com.springboot.applicationspringboot.Model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Data
@Component
public class Person {
	private String name;
    private String last;
   
}
