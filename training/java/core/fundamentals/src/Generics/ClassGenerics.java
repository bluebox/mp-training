package Generics;
class Element <E>
{
	private E value;
	Element(E v)
	{
		value=v;
	}
	public E getValue()
	{
		return value;
	}
}
public class ClassGenerics {
	public static void main(String args[])
	{
		Element<Integer> el=new Element<>(23);
		el.getValue();
		System.out.print(el.getValue());
		Element<String> el2=new Element<>("Pavabn");
		el.getValue();
		System.out.print(el2.getValue());
	}
}
