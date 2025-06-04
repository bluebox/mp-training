import java.util.*;

class Items {
	String name;
	int totalItems;
	public Items(String name,int totalItems) {
		this.name=name;
		this.totalItems=totalItems;
	}
}
class ItemsOrder implements Comparator<Items>{

	@Override
	public int compare(Items a, Items b) {
		int val=a.name.compareTo(b.name);
		if(val!=0)return val;
		return a.totalItems-b.totalItems;
	}
	
}
public class ComparatorExample {

	public static void main(String[] args) {
		List<Items>arr=new ArrayList<>(Arrays.asList(new Items("biscuit",20),new Items("gold",10),new Items("gold",20)));
	    Collections.sort(arr,new ItemsOrder());
		arr.sort((a,b)->a.name.compareTo(b.name));
	    arr.sort(new ItemsOrder());
	    for(Items ele:arr)System.out.println(ele.name+" "+ele.totalItems);
	    List<String>val=Arrays.asList("abhi","naresh","anand");
	    val.sort(Comparator.naturalOrder());
	    for(String ele:val)System.out.println(ele);
	}

}
