import java.util.*;

public class ComplexNumbers {
	double real;
	double imaginary;
	ComplexNumbers(double real,double imaginary)
	{
		this.real=real;
		this.imaginary=imaginary;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		ComplexNumbers c1=new ComplexNumbers(1,1);
		ComplexNumbers c2=new ComplexNumbers(3,3);
		ComplexNumbers c3=new ComplexNumbers(4,4);
		int n=sc.nextInt();
		for(int i=0;i<n;i++)
		{
			System.out.println("enter the value for iteration "+i);
		c1=c1.add(sc.nextInt(), sc.nextInt());
		}
		System.out.println(c1.getImaginary());
		System.out.println(c1.getReal());
		

	}
	public double getReal() {
		return real;
	}
	public double getImaginary()
	{
		return imaginary;
	}
	public ComplexNumbers add(double real,double imaginary)
	{
		return new ComplexNumbers(this.real+real,this.imaginary+imaginary);
	}
	public ComplexNumbers sub(double real,double imaginary)
	{
		return new ComplexNumbers(this.real-real,this.imaginary-imaginary);
	}

}
