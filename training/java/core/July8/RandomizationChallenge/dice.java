package July8.RandomizationChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class dice {
	private static final Random r = new Random();
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		List<Integer> current = new ArrayList<>();
//		 
//		 do { 
//		 rollDice(current); 
//		 current.clear(); 
//		 }while(++rolls < 5);
		 
		do {
			rollDice(current);
			//current.clear();
		} while (!pickLosers(current));
		
		System.out.println("Game Over");
	}

	private static void rollDice(List<Integer> current) {
		int randomCount = 5 - current.size();
		
		var out = r.ints(randomCount, 1, 7)
				.sorted()
				.boxed()
				.toList();
		
		current.addAll(out);
		System.out.println("You're dice are : " + current);
	}

	private static boolean pickLosers(List<Integer> current) { 
		
		String prompt = """
				Press Enter to Score. 
				Type "ALL" to re-roll all the dice. 
				List Numbers (separated by spaces) to re-roll selected dice. 
				""" ;
		
		System.out.print(prompt + "--> "); 
		String userInput = sc.nextLine(); 
		if(userInput.isBlank()) return true; 
		
		try { 
			removeDice(current, userInput.split(" ")); 
			
			} catch(Exception e) { 
				e.printStackTrace(System.out); 
				System.out.println("Bad input, Try Again"); 
				} 
		return false; 
		}

	private static void removeDice(List<Integer> current, String[] selected) {
		if (selected.length == 1 && selected[0].equalsIgnoreCase("ALL")) {
			current.clear();
		} else {
			for (String removed : selected) {
				current.remove(Integer.valueOf(removed));
			}
			rollDice(current);
		}
	}
}
