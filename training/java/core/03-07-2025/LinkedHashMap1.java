import java.util.*;


class Marks
{
	
	String name;
	int marks;
	String subject;
	
	
	public Marks(String name, int marks, String subject) {
		this.name = name;
		this.marks = marks;
		this.subject = subject;
	}


	@Override
	public String toString() {
		return " Marks [name=" + name + ", marks=" + marks + ", subject=" + subject + "]";
	}
	
	
}

public class LinkedHashMap1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		LinkedHashMap<Integer,Marks> list=new LinkedHashMap<>();
		 LinkedHashMap<Integer, Marks> list = new LinkedHashMap<>();
		
		list.put(1,new Marks("Tarun",96,"Maths"));
		list.put(2,new Marks("Sai",96,"English"));
		
		
		System.out.println(list);
		

	}

}
