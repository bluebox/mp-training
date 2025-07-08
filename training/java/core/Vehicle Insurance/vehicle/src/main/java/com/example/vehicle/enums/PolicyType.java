package com.example.vehicle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PolicyType {
	Silver("silver",100000,2000),Gold("gold",500000,8000),Platinum("platinum",1000000,15000);
	String ptype;
	private double premiumAmount;
	private double policyAmount;	
}

