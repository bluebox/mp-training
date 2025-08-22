public class AnonymousInner { 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B b=new B();
		b.print();
		b.show();
		B b1=new B()
				{
					public void print()
					{
						System.out.println("updated print method");
					}
				};
				b1.print();
	}
}
class A
{
	public void show()
	{
		System.out.println("In show method");
	}
	public void print()
	{
		System.out.println("print method");
	}
}
class B extends A
{
	public void print()
	{
		System.out.println("updated print method");
	}
}
