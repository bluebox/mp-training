
public class ComplexNumberOperations {
	private double real;
	private double imaginary;
	ComplexNumberOperations(double real,double imaginary)
	{
		this.real=real;
		this.imaginary=imaginary;
	}
	public double getReal()
	{
		return real;
	}
	public double getImaginary()
	{
		return imaginary;
	}
	public  ComplexNumberOperations add(double real,double imaginary)
	{
		return new ComplexNumberOperations(this.real+real,this.imaginary+imaginary);
	}
	public ComplexNumberOperations add(ComplexNumberOperations c1)
	{
		return new ComplexNumberOperations(this.real+c1.getReal(),this.imaginary+c1.getImaginary());
	}
	public ComplexNumberOperations subtract(double real,double imaginary)
	{
		return new ComplexNumberOperations(this.real-real,this.imaginary-imaginary);
	}
	public ComplexNumberOperations subtract(ComplexNumberOperations c1)
	{
		return new ComplexNumberOperations(this.real-c1.getReal(),this.imaginary-c1.getImaginary());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ComplexNumberOperations one =new ComplexNumberOperations(1.0,1.0);
		ComplexNumberOperations number =new ComplexNumberOperations(2.5,-1.5);
		one=one.add(1,1);
		System.out.println("one.real= "+one.getReal());
		System.out.println("one.imaginary= "+one.getImaginary());
		one=one.subtract(number);
		System.out.println("one.real= "+one.getReal());
		System.out.println("one.imaginary= "+one.getImaginary());
		number=number.subtract(one);
		System.out.println("one.real= "+number.getReal());
		System.out.println("one.imaginary= "+number.getImaginary());
		
	}
	

}
