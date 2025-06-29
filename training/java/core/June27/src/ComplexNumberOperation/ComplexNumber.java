package ComplexNumberOperation;

public class ComplexNumber {
	private double real;
	private double imaginary;
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public double getReal() {
		return real;
	}
	
	public double getImaginary() {
		return imaginary;
	}
	
	public void add(double real, double imaginary) {
		this.real += real;
		this.imaginary += imaginary;
	}
	
	public void add(ComplexNumber number) {
		real += number.getReal();
		imaginary += number.getImaginary();
	}
	
	public void substract(double real, double imaginary) {
		this.real -= real;
		this.imaginary -= imaginary;
	}
	
	public void substract(ComplexNumber number) {
		real -= number.getReal();
		imaginary -= number.getImaginary();
	}
	
	
}
