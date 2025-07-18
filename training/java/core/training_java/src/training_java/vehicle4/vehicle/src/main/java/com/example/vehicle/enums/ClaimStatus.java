package com.example.vehicle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClaimStatus {
	Accepted("A"),Initaited("I"),Rejected("R");
	String status;
}
