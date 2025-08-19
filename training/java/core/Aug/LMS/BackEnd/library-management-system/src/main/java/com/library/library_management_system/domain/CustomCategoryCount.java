package com.library.library_management_system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomCategoryCount {
	private String category;
	private int count;
}
