package com.spring.secure.security.beans;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	
	private String name;
	private int age;
	private long contact;
	private String address;

}
