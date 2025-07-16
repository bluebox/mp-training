package RollingDice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class RandomDiceChallenge {

	private static final Random random=new Random();
	
	private static final Scanner input=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		List<Integer> Dice=new ArrayList<>();
		
		
		do {
			
			rollingDice(Dice);
			
			Dice.clear();
		}while(!pick_Losers(Dice));
		
		System.out.println("Game Over.");

	}

	public static void rollingDice(List<Integer> Dice) {
		
		int random_Count = 5-Dice.size();
		
		var newDice = random.ints(random_Count,1,7).sorted().boxed().toList();
		
		Dice.addAll(newDice);
		
		System.out.println("your Dice are: "+Dice);
		
		
	}
	
	private static boolean pick_Losers(List<Integer> Dice) {
		
		String Menu_to_choose="""
				Press ENTER to score.
				Type "ALL" to re-roll all dice.
				List Numbers(Separated by spaces to re-roll).
				""";
		
		System.out.println(Menu_to_choose + " --> ");
		
		String Input_of_user=input.nextLine();
		
		if(Input_of_user.isBlank()) {
			return true;
		}
		
		removeDice(Dice,Input_of_user.split(" "));
		return false;
	}
	
	private static void removeDice(List<Integer> Dice,String[] choosen_Dice) {
		
		if(choosen_Dice.length==1 && choosen_Dice[0].contains("ALL")) {
			
			Dice.clear();
		}
		else {
			
			for(String removed:choosen_Dice) {
				Dice.remove(Integer.valueOf(removed));
			}
		}
	}
}
