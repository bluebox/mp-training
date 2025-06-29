class Parent1
{
	public void method()
	{
		System.out.println("This is the parent class method");
	}
}

class Child1 extends Parent1
{
	public void method()
	{
		System.out.println("This is the Child class method");
	}
}
public class MethodOverriding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Child1 ch= new Child1();
		ch.method();

	}

}
