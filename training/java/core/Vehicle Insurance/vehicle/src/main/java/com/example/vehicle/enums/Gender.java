package com.example.vehicle.enums;

public enum Gender {
	MALE('M'), FEMALE('F');

	private char val;

	Gender(char val) {
		this.val = val;
	}

	public char getVal() {
		return val;
	}
}
