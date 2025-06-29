public class ComplexNumberCalc {
    static class ComplexNumber {
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
public static void main(String[] args) {
    ComplexNumber cm = new ComplexNumber(1,2);
		cm.add(1,2);
		System.out.println(cm.getReal());
		System.out.println(cm.getImaginary());
		
		ComplexNumber cm1 = new ComplexNumber(-1.4,3);
		cm.subtract(cm1);
		
		System.out.println(cm.getReal());
		System.out.println(cm.getImaginary());  
}
}
