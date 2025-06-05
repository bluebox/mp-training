package GenericsPractise;

public class Practise_1
{
	public static void main(String [] args)
	{
		Box<Integer> intBox = new Box<Integer>();
		Box<String> stringBox = new Box<String>();
		
		intBox.add(new Integer(56));
		stringBox.add(new String("Blue World"));
		
		System.out.printf("Generic Box with Integer type: %d\n",intBox.get());
		System.out.printf("Generic Box with String type: %s\n",stringBox.get());
	}
}
class Box <T>
{
	private T t;
	
	public void add(T t)
	{
		this.t = t;
	}
	public T get()
	{
		return this.t;
	}
}