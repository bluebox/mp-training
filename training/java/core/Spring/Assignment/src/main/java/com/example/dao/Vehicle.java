package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;

public class Vehicle {
	@Autowired(required = false)
	Speaker s;
	public Speaker speaker() {
		return s;
	}
	@Autowired(required = false)
	Tyres t;
}
