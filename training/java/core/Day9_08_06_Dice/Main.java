package Day9_08_06_Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
	private static final Random random=new Random();
	private static final Scanner scanner=new Scanner(System.in);
	public static void main(String args[]) {
		List<Integer> currentDice = new ArrayList<>();
		do {
			rollDice(currentDice);
		}while(losers(currentDice));
		System.out.println("Game over...");
		
	}
	private static void rollDice(List<Integer> currentDice) {
		int noOfDiceToRoll=5-currentDice.size();
		var newDice=random.ints(noOfDiceToRoll,1,7)
				.boxed()
				.toList();
		currentDice.addAll(newDice);
		System.out.println(currentDice);
	}


	private static boolean losers(List<Integer> currentDice) {
		String prompt="""
				Press Enter to Score.
				Type "ALL" to re-roll all the dice.
				List numbers (seperate by spaces) to re roll selected dice.
				""";
		System.out.print(prompt+"---> ");
		String userInput=scanner.nextLine();
		if(userInput.isBlank()) {
			return false;
		}
		try {
			removeDice(currentDice,userInput.split(" "));
		}catch(Exception e) {
			System.out.println(e);
			System.out.println("Bad input");
		}
		return true;
	}

	private static void removeDice(List<Integer> currentDice,String[] selected) {
		if(selected.length==1 && selected[0].toUpperCase().equals("ALL")) {
			currentDice.clear();
		}else {
			for(String remove:selected) {
				currentDice.remove(Integer.valueOf(remove));
			}
			System.out.println("Keeping "+currentDice);
		}
	}
}
