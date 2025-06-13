package LinkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class ListIterator {

	public static void main(String[] args) {
		LinkedList<Integer>ans=new LinkedList<>();
		ans.add(20);
		ans.add(40);
		ans.add(40);
		ans.pop();
		Iterator<Integer>it=ans.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
