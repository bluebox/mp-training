package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LevelOrdering {
	public static HashMap<Integer, ArrayList<Integer>> l = new HashMap<>();

	public void levelOrdering(Tree t) {
		order(t, 0);
		l.values().stream().forEach(l -> l.stream().forEach(i -> System.out.print(i + " ")));
	}

	public void order(Tree t, int i) {
		if (t == null) {
			return;
		}
		if (l.containsKey(i)) {
//			ArrayList<Integer> t1 = l.get(i);
//			t1.add(t.data);
			l.get(i).add(t.data);
//			l.put(i, l.get(i).add(t.data);

		} else {
			l.put(i, new ArrayList<>(List.of(t.data)));
		}
		order(t.left, i + 1);
		order(t.right, i + 1);
	}
}
