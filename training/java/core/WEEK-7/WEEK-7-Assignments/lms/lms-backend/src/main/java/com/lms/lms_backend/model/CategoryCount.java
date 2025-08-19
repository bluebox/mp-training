package com.lms.lms_backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryCount {
	private String category;
	private int count;
}
