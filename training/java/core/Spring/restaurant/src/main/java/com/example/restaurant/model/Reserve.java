package com.example.restaurant.model;

import java.util.Date;

import lombok.Data;

@Data
public class Reserve {
	
	private String name;
	private Date date;
	private String time;
}
