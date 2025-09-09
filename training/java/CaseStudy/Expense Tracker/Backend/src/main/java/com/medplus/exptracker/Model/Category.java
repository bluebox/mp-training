package com.medplus.exptracker.Model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Category {
	Integer id;
	String name;
	BigDecimal monthly_limit;
}
