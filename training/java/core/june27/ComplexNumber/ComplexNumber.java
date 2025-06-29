package com.tulasidhar.june27.ComplexNumber;

public class ComplexNumber {
	double real,imaginary;

	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public void add(double real,double imaginary) {
		this.real += real;
		this.imaginary += imaginary;
	} 
	public void add(ComplexNumber cm) {
		this.real += cm.real;
		this.imaginary += cm.imaginary;
	}

	public void subtract(double real,double imaginary) {
		this.real -= real;
		this.imaginary -= imaginary;
	}
	
	public void subtract(ComplexNumber cm) {
		this.real -= cm.real;
		this.imaginary -= cm.imaginary;
	}
	
	
	

	public double getReal() {
		return real;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}
	
	
	
	
}
