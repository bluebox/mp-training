package com.example.springbootexample.demospringboot;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Component
public class Product {
	private String productname;
	private double price;

}
