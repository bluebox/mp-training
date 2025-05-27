package arraylistmethods;

import java.util.*;

public class Main {
	public static void main(String args[]) {
		int[] arr = new int[] { 1, 2, 3, 4, 5 };
		ArrayList<Integer> list = new ArrayList<>();
		List<Integer> immutableList = List.of(1, 2, 3, 4);
		System.out.println(immutableList);
		try {
			immutableList.add(3);

		} catch (Exception e) {
			System.out.println("Its immuatble " + e);
		}
		System.out.println(immutableList);

		list.addAll(immutableList);
		int length = list.size();
		System.out.println(list + "of size " + length);
		// Adding other elements
		list.add(5);
		list.add(2, 6);
		System.out.println("After Adding elements " + list);
		if (list.contains(5)) {
			System.out.println("element 5 is present in list");

		}
		list.sort(Comparator.naturalOrder());
		System.out.println("After sorting " + list);

		list.set(0, 9);
		System.out.println(list);
		list.clear();
		System.out.println("After clearing " + list);

	}

}
