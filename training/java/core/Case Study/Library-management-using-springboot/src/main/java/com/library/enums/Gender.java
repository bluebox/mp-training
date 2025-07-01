package com.library.enums;

public enum Gender {
	Male('M'),Female('F');
	char gender;
	Gender(char c) {
		this.gender=c;
	}
	public char getGender() {
		return this.gender;
	}
	public void setGender(char gender) {
		this.gender=gender;
	}
}