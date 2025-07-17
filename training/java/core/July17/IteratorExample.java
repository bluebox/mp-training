package July17.Practice;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorExample {

	public static void main(String[] args) {

		ArrayList<Integer> al = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			al.add(i);
		}
		System.out.println("Original List: " + al);
		Iterator<Integer> itr = al.iterator();
		while (itr.hasNext()) {
			int i = itr.next();
			System.out.print(i + " ");
			if (i % 2 != 0) {
				itr.remove();
			}
		}
		System.out.println();
		System.out.println("Modified List: " + al);
	}
}
