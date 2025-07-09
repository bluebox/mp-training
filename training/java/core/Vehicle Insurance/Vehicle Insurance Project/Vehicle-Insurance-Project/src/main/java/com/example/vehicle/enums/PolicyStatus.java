package com.example.vehicle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PolicyStatus {
	Requested("R"),Active("A"),InActive("I");
	String c;
}
