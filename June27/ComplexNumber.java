package June27;

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

	public void setReal(double real) {
		this.real = real;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}
	
	public void add(double real,double imaginary) {
    	this.real=this.real+real;
    	this.imaginary=this.imaginary+imaginary;
    }
    
    public void add(ComplexNumber number) {
    	this.real=this.real+number.real;
    	this.imaginary=this.imaginary+number.imaginary;
    }
    
    public void subtract(double real,double imaginary) {
    	this.real=this.real-real;
    	this.imaginary=this.imaginary-imaginary;
    }
    
    public void subtract(ComplexNumber number) {
    	this.real=this.real-number.real;
    	this.imaginary=this.imaginary-number.imaginary;
    }
}
