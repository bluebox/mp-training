package Weekproblems;

import java.util.HashSet;
import java.util.Set;

public class DailyProblem_16_7_2025 {

	public static void main(String[] args) {
		int[] num = { 0, 0, 10, 20, 90 };
		StringBuilder output = new StringBuilder("");
//		List<Long>max=new ArrayList<>();
//		max.add(Long.MIN_VALUE);
		Long max = Long.MIN_VALUE;
		Set<Integer> set = new HashSet<>();
		max = combinations(num, output, 0, max, set);
		System.out.println(max.toString());

	}

	public static Long combinations(int[] num, StringBuilder output, int j, Long max, Set<Integer> set) {

		if (j == num.length) {
			String str = output.toString();
			if (max < Long.parseLong(str)) {
				max = Long.parseLong(str);
			}
			return max;
		}

		for (int i = 0; i < num.length; i++) {
			if (!set.contains(i)) {

				set.add(i);
				int originallength = output.length();
				output.append("" + num[i]);
				long max1 = combinations(num, output, j + 1, max, set);
				if (max < max1) {
					max = max1;
				}
				output.setLength(originallength);
				set.remove(i);
			}
		}
		return max;
	}

}
