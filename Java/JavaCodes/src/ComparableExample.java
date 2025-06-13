import java.util.*;

class Number implements Comparable<Number>{
    public int no;
    public Number(int no) {
    	this.no=no;
    }
	@Override
	public int compareTo(Number o) {
		return this.no-o.no;
	}
}
class Pair implements Comparable<Pair>{
	String name;
	int age;
	public Pair(String name,int age) {
		this.age=age;
		this.name=name;
	}
	public int compareTo(Pair a) {
		int val=a.name.compareTo(this.name);
		if(val!=0)return val;
		return a.age-this.age;
	}
}

public class ComparableExample {
    public static void main(String[]args) {  
    	Number[]ans= {new Number(10),new Number(20)};
    	Arrays.sort(ans);
    	for(Number ele:ans) {
    		System.out.println(ele.no);
    	}
    	List<Pair>val=new ArrayList<>(Arrays.asList(new Pair("anand",22),new Pair("abhi",22),new Pair("Naresh",22)));
    	Collections.sort(val);
    	for(Pair ele:val)System.out.println(ele.name+" "+ele.age);
    }
}
