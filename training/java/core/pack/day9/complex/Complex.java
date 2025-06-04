package day9.complex;

public class Complex {
private double real;
private double imaginary;
Complex(double real,double imaginary){
	this.real=real;
	this.imaginary=imaginary;
}

public double getReal() {
	return this.real;
}

public double getImaginary() {
	return this.imaginary;
}

public void add(double real,double imaginary) {
	this.real+=real;
	this.imaginary+=imaginary;
}
public void add(Complex c) {
	this.real+=c.real;
	this.imaginary+=c.imaginary;
}
public void subtract(double real,double imaginary) {
	this.real-=real;
	this.imaginary-=imaginary;
}
public void subtract(Complex c) {
	this.real-=c.real;
	this.imaginary-=c.imaginary;
}
}
