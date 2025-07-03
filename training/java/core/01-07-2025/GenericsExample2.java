import java.util.*;
class Box<T> 
{
	ArrayList<T> dryFruits=new ArrayList<>();
	public void add(T dryFruit)
	{
		dryFruits.add(dryFruit);
	}
	
	public T get(int i)
	{
		return dryFruits.get(i);
	}
}
class Cashew1
{
	String cashewname;
	public  Cashew1(String cashewname)
	{
		this.cashewname=cashewname;
	}
	@Override
	public String toString() {
		return "Cashew1 [cashewname=" + cashewname + "]";
	}
	
}

class Almond1
{
	String almondname;
	public Almond1(String almondname)
	{
		this.almondname=almondname;
	}
	@Override
	public String toString() {
		return "Almond1 [almondname=" + almondname + "]";
	}
	
}
public class GenericsExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box<Almond1> almondbox=new Box<>();
		almondbox.add(new Almond1("Indian almond"));
		almondbox.add(new Almond1("African almond"));
		almondbox.add(new Almond1("American almond"));
		almondbox.add(new Almond1("Japaneese almond"));
		System.out.println(almondbox.get(2));
		
		Box<Cashew1> cashewbox=new Box<>();
		cashewbox.add(new Cashew1("Indian Cashew"));
		cashewbox.add(new Cashew1("American Cashew"));
		cashewbox.add(new Cashew1("Jpaneese Cashew"));
		cashewbox.add(new Cashew1("African Cashew"));
		System.out.println(cashewbox.get(3));
		
	}

}
