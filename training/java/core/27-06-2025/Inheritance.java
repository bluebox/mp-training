class Parent{
	String str1="Hello Every one";
	 void parent()
	 {
		 System.out.println("This is parent class");
	 }
}

class Child extends Parent
{
	String str="Welcome";
	void child()
	{
		System.out.println("This is child class");
	}
}

public class Inheritance extends Child {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inheritance i=new Inheritance();
		i.parent();
		i.child();
		System.out.println(i.str);
		

	}

}
