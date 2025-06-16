package iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
	public static void main(String[] args)
	{
		ArrayList<Integer> lst=new ArrayList<Integer>();
		lst.addAll(List.of(1,2,3,4,5,6));
		Iterator<Integer> iterator=lst.iterator();
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+" ");
			
		}
		
		
	}

}
