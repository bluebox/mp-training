
import java.util.ArrayList;
class Almond
{
	String name;
	public Almond(String name)
	{
		this.name=name;
	}
	@Override
	public String toString() {
		return "Almond [name=" + name + "]";
	}
	
}

class Cashew
{
	String cashewname;
	public Cashew(String cashewname)
	{
		this.cashewname=cashewname;
	}
	@Override
	public String toString() {
		return "Cashew [cashewname=" + cashewname + "]";
	}
	
}

class AlmondBox
{
	ArrayList<Almond> almondlist=new ArrayList<>();
	public void add(Almond almond)
	{
		almondlist.add(almond);
	}
}

class CashewBox
{
	ArrayList<Cashew> cashewbox=new ArrayList<>();
	public void add(Cashew cashew)
	{
		cashewbox.add(cashew);
	}
}
public class GenericsChallenge1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlmondBox a1=new AlmondBox();
		a1.add(new Almond("Indian almond"));
		a1.add(new Almond("African almond"));
		a1.add(new Almond("American almond"));
		a1.add(new Almond("Italian almond"));
		System.out.println(a1.almondlist.get(2));
		
		CashewBox c1=new CashewBox();
		c1.add(new Cashew("Indian Cashew"));
		c1.add(new Cashew("African Cashew"));
		c1.add(new Cashew("American Cashew"));
		c1.add(new Cashew("Italian Cashew"));
		System.out.println(c1.cashewbox.get(2));

	}

}
