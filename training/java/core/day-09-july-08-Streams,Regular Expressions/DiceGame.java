package day9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DiceGame {

	public static Random random = new Random();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		List<Integer> faces = random.ints(1, 7).limit(5).boxed().collect(Collectors.toList());
		do {
			System.out.println("faces are " + faces);
			System.out.println("1. enter \"ALL\" to replace all");
			System.out.println("2. enter numbers to replace only them. eg: 2,3,5");
			System.out.println("3. just press ENTER to exit");
			String input = sc.nextLine().trim();
			if (input.equalsIgnoreCase("ALL")) {
				System.out.println("rolling all dice");
				faces = random.ints(1, 7).limit(5).boxed().collect(Collectors.toList());
			} else if (input.isEmpty()) {
				System.out.println("Exiting....");
				return;
			} else {
				try {
					List<Integer> toChange = new ArrayList<>();
					for (String str : input.split(",")) {
						toChange.add(Integer.parseInt(str.trim()));
					}
					faces = roll(faces, toChange);
				} catch (Exception e) {
					System.out.println("invalid input");
				}
			}
		} while (true);
	}

	private static List<Integer> roll(List<Integer> faces, List<Integer> toChange) {
		boolean found = false;
		for (int num : toChange) {
			if (faces.indexOf(num) > -1) {
				found = true;
				faces.set(faces.indexOf(num), random.nextInt(6) + 1);
			}
		}
		if (!found)
			System.out.println("element not found");
		return faces;
	}
}
