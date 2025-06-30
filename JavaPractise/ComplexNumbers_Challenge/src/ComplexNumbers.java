
public class ComplexNumbers {
	private double real;
	private double imaginary;
	public ComplexNumbers(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
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
	public void add(double a,double b) {
		real+=a;
		imaginary+=b;
	}
	public void add(ComplexNumbers comp) {
		double a=comp.getReal();
		double b=comp.getImaginary();
		real+=a;
		imaginary+=b;
	}
	public void subtract(double a,double b) {
		real-=a;
		imaginary-=b;
	}
	public void subtract(ComplexNumbers comp) {
		double a=comp.getReal();
		double b=comp.getImaginary();
		real-=a;
		imaginary-=b;
	}
	
	
}
