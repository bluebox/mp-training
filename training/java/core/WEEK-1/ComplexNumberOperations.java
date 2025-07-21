class ComplexNumber {
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

public class ComplexNumberOperations {
    public static void main(String[] args) {
        ComplexNumber cm1 = new ComplexNumber(2.0, 3.0);
        ComplexNumber cm2 = new ComplexNumber(1.0, 4.0);
        
        cm1.add(cm2);
        System.out.println("After addition: " + cm1.getReal() + " + " + cm1.getImaginary() + "i");
        
        cm1.subtract(1.0, 2.0);
        System.out.println("After subtraction: " + cm1.getReal() + " + " + cm1.getImaginary() + "i");
    }
}