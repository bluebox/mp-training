abstract class Animal1
{
	Animal1()
	{
		System.out.println("This is Animal constructor");
	}
	
	public abstract void method1();
}

class Child2 extends Animal1
{
	public void method1()
	{
		System.out.println("I am in child class");
	}
}
public class AbstractionChallenge3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Child2 c=new Child2();
		c.method1();
	}

}
