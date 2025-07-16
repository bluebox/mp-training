package corejava.july8_random;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
//import java.util.TreeSet;

public class Dice {
	private static final Random random=new Random();
	private static final Scanner sc= new Scanner(System.in);
	public static void main(String[] args) {
		List<Integer> dice=new ArrayList<>();
		do {
			rollDice(dice);
		}while(!looser(dice));
		System.out.println("Game over. Real game would score and continue.");
		sc.close();
	}
	private static void rollDice(List<Integer> dice) {
		int randomNumber=5-dice.size();
		var newDiceValues=random.ints(randomNumber,1,7)
				.sorted()
				.boxed().
				toList();
		dice.addAll(newDiceValues);
		System.out.println("you're dice are: "+dice);
	}
	private static boolean looser(List<Integer> dice) {
		String display="""
				Press Enter to Score.
				Type "ALL" to re-roll all the dice.
				List numbers[eg: 4 5] to re-roll selected dice.
				""";
		System.out.print(display+"---> ");
		String option=sc.nextLine();
		if(option.isBlank())
			return true;
		try {
			removeDice(dice,option.split(" "));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Invalid Input!");
		}
		return false;
		
	}
	private static void removeDice(List<Integer> dice,String[] selected) {
		if(selected.length==1 && selected[0].contains("ALL"))
			dice.clear();
		else {
			List<String> indexs=new ArrayList<>(Arrays.asList(selected));
			/*
			 * List<Integer> removeIndexs=new ArrayList<>(); for(String myIndex:indexs) {
			 * int y=indexs.indexOf(myIndex); int x=indexs.lastIndexOf(myIndex); if(x!=y)
			 * removeIndexs.add(x); } for(Integer rindex:removeIndexs) {
			 * dice.removeLast(rindex); }
			 */
			LinkedHashSet<String> set=new LinkedHashSet<>(indexs);
			List<String> removeIndexs=new ArrayList<>(set);
			
			Collections.sort(removeIndexs,Collections.reverseOrder());
			System.out.println(removeIndexs);
			for(String index:removeIndexs) {
				dice.remove(Integer.parseInt(index)-1);
			}
		}
	}
}
