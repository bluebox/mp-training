package diceGameChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiceMain {
	  
	static List<Integer> DiceResults = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DiceValues();
		while(true) {
			System.out.println("Press Enter to Score");
			System.out.println("Type `all` to re-roll the dice.");
			System.out.println("List numbers (separated by space) to re-roll selected dice.");
			String option = sc.nextLine();
			if(checkOptions(option)) {
				break;
			}
		}
		System.out.println("Game Over");
		sc.close();
	}
	
	public static void DiceValues(){ 
		
		List<Integer> stream = new Random().ints(5-DiceResults.size(),1,7).boxed().collect(Collectors.toList());
		DiceResults.addAll(stream);
		System.out.println("Dice Values ");
		System.out.println(DiceResults);

	}
	
	public static boolean checkOptions(String option) {
		
		if(option.trim().length() == 0) {
			return true;
		}
		
		if(option.contains("all")) {
			DiceResults.clear();
			DiceValues();
			return false;
		}
		
		String[] reDiceValues = option.split(" ");
		List<Integer> list =Stream.of(reDiceValues)
				.map(i->Integer.parseInt(i))
				.collect(Collectors.toList());
		DiceResults.removeAll(list);
		System.out.println("Keeping");
		System.out.println(DiceResults);
		if(DiceResults.size() == 5) {
			System.out.println("enter correct values");
			return false;
		}
		DiceValues();
		return false;
	}
	
}
