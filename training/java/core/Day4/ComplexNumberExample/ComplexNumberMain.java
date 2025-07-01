package ComplexNumberExample;

public class ComplexNumberMain {
public static void main(String []args)
{
	ComplexNumber c1=new ComplexNumber(1.0,1.0);
	ComplexNumber c2=new ComplexNumber(2.5,-1.5);
	c1.add(1,1);
	System.out.println("real : "+c1.getReal()+ "  Imaginary :"+c1.getImaginary());
	c2.substract(2.5,-1.5);
	System.out.println("real : "+c2.getReal()+ "  Imaginary :"+c2.getImaginary());

	
}
}
