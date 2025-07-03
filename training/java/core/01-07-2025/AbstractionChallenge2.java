 abstract class Parent
{
	abstract void method1();
	
	void parent()
	{
		System.out.println("This is parent class");
	}
}
 
 class Child extends Parent
 {
	 void method1()
	 {
		 System.out.println("This is child  class");
	 }
 }
 
 class GrandChild extends Parent
 {
	 void method1()
	 {
		 System.out.println("This is grand child class");
	 }
 }
public class AbstractionChallenge2 {
	public static void main(String args[])
	{
		
		Child c=new Child();
		GrandChild g=new GrandChild();
		c.method1();
		g.method1();
		c.parent();
		
	}

}
