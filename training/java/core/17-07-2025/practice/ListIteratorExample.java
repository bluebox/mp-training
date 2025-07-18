package practice;

import java.util.ArrayList;
import java.util.ListIterator;


public class ListIteratorExample {

	public static void main(String[] args) {
		ArrayList<Integer> a1=new ArrayList<>();
		a1.add(1);
		a1.add(2);
		a1.add(3);
		
		
		ListIterator<Integer> it = a1.listIterator();
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		while(it.hasPrevious())
		{
			System.out.println(it.previous());
		}

	}

}
