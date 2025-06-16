package Generics;

class Adition
{
	public <T extends Integer> void add(T a,T b)
	{
		System.out.println(a+b);
	}
}
public class MethodGenerics {
	public static void main(String args[])
	{
		Adition object=new Adition();
		object.add(1,4);
	}
}
