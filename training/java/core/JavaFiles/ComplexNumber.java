
public class ComplexNumber {
	private double Real;
	private double Imaginary;
	
	
	public ComplexNumber(double real, double imaginary) {
		Real = real;
		Imaginary = imaginary;
	}

	public double getReal() {
		return Real;
	}


	public double getImaginary() {
		return Imaginary;
	}
	
	public void add(double real,double imaginary) {
		 Real+=real;
		Imaginary+=imaginary;
	}
	
	public void add(ComplexNumber complex) {
		Real+=complex.Real;
		Imaginary+=complex.Imaginary;
	}
	
	public void substract(double real,double imaginary){
		Real-=real;
		Imaginary-=imaginary;
	}
	
	public void substract(ComplexNumber complex) {
		Real-=complex.Real;
		Imaginary-=complex.Imaginary;
	}
	
	public static void main(String[] args) {
		
		ComplexNumber com =new ComplexNumber(1.0,1.0);
		ComplexNumber com1=new ComplexNumber(2.5,-1.5);
		
		com.add(1,1);
		
		System.out.println("com.real ="+com.getReal());
		System.out.println("com.imaginary ="+com.getImaginary());
		
		com.substract(com1);
		System.out.println("com.real ="+com.getReal());
		System.out.println("com.imaginary ="+com.getImaginary());
		com1.substract(com);
		System.out.println("com.real ="+com1.getReal());
		System.out.println("com.imaginary ="+com1.getImaginary());

		
	}

}
