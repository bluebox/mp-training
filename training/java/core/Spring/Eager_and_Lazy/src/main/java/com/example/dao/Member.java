package com.example.dao;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
public class Member {
	@PostConstruct
	public void initialize() {
		System.out.println("Intialisation done in member class");
	}
}
