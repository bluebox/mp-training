package com.example.demo.lombok;

import lombok.*;

@Data // equivalent to all previous annotations
@Builder
public class Book {
	private String name;
	private Integer price;
}