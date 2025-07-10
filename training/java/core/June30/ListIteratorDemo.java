package June30;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class ListIteratorDemo {
	public static void main(String[] args) {
		
		//ArrayList<String> arrList = new ArrayList<>();
		LinkedList<String> arrList=new LinkedList<>();
		arrList.add("Hello");
		arrList.add("This");
		arrList.add("Course");
		arrList.add("Is");
		arrList.add("Java");
		
		ListIterator <String> it = arrList.listIterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		while(it.hasPrevious()) {
			System.out.println(it.previous());
		}
		
	}
}
