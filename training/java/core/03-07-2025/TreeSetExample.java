import java.util.*;

class Names implements Comparable<Names>
{
	String name;
	String city;
	
	
	public Names(String name, String city) {
		this.name = name;
		this.city = city;
	}


	@Override
	public String toString() {
		return "Names [name=" + name + ", city=" + city + "]";
	}
	
	public int compareTo(Names o)
	{
		return this.name.compareTo(o.name);
	}
	
	
}
public class TreeSetExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeSet<Names> tset=new TreeSet<>();
		tset.add(new Names("tarun","Srikakulam"));
		tset.add(new Names("Raju","VSKP"));
		
		System.out.println(tset);

	}

}
