import java.lang.*;
sealed class Car permits Benz,Thar,Honda
{
	public void print()
	{
		System.out.println("This is Car class");
	}
}
final class Benz extends Car
{
	public void print()
	{
		System.out.println("This is Benz class");
	}
}

final class Thar extends Car
{
	public void print()
	{
		System.out.println("This is Thar class");
	}
}
 

final class Honda extends Car
{
	public void print()
	{
		System.out.println("This is Honda class");
	}
}


public class SealedClassExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car c1=new Benz();
		c1.print();
		Car c2=new Thar();
		c2.print();
		Car c3=new Honda();
		c3.print();

	}

}
