class ComplexNumber
{
	double real,imaginary;

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

	
	public void add(double real, double imaginary)
	{
		this.real+=real;
		this.imaginary+=imaginary;
	}
	
	public void add(ComplexNumber other)
	{
		this.real+=other.real;
		this.imaginary+=other.imaginary;
	}
	
	public void substract(double real, double imaginary)
	{
		this.real-=real;
		this.imaginary-=imaginary;
	}
	
	public void substract(ComplexNumber other)
	{
		this.real-=other.real;
		this.imaginary-=other.imaginary;
	}
	
}
public class ComplexChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComplexNumber one=new ComplexNumber(1.0,1.0);
		ComplexNumber number=new ComplexNumber(2.5,-1.5);
		one.add(1,1);
		System.out.println("one.real="+one.getReal());
		System.out.println("one.imaginary="+one.getImaginary());
		one.substract(number);
		System.out.println("one.real="+one.getReal());
		System.out.println("one.imaginary="+one.getImaginary());
		number.substract(one);
		System.out.println("number.real="+number.getReal());
		System.out.println("number.imaginary="+number.getImaginary());

	
		
		

	}

}
