package Dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;
enum Scores{
	ACES,TWOS,THREES,FOURS,FIVES;
	private Integer score= null;
	public void setScore(int num){
		score=num;
	}
	public Integer getScore() {
		return score;
	}
	public String toString() {
		return "%s %s %s %s %s".formatted(ACES.score,TWOS.score,THREES.score,FOURS.score,FIVES.score);
				}
	
}
public class RollDice {
	private static Random random=new Random();
	private static Scanner scanner=new Scanner(System.in);
	public static List<Integer> rollDice(List<Integer>currentDice){
		int count =5-currentDice.size();
		var rollDice=random.ints(count,1,7).sorted().boxed().toList();
		currentDice.addAll(rollDice);
		Collections.sort(currentDice);
		return currentDice;
	}
	public static void  displayMenu() {
		System.out.println("1.Enter (R) to Roll dice");
		System.out.println("2.Enter (Q) to Quit");
	}
	public static void changeDice(List<Integer> currentDice) {
		System.out.println("1.Enter numbers to reroll");
		System.out.println("2.press Enter to fix your choice");
		String s=scanner.nextLine();
		while(!s.isEmpty()) {
			String[] strings=s.split(" ");
			var changeNumbers=Arrays.stream(strings).mapToInt(i->Integer.valueOf(i)).toArray();
			for(var i:changeNumbers) {
				currentDice.remove(Integer.valueOf(i));
			}
			currentDice=rollDice(currentDice);
			System.out.println("Your Updtaed Dice :"+currentDice);
			System.out.println("1.Enter numbers to reroll");
			System.out.println("2.press Enter to reset all your dice");
			s=scanner.nextLine();	
		}
	}
	public static void main(String[] args) {
		List<Integer> currentDice=new ArrayList<Integer>();
		System.out.println("Enter name :");
		String name=scanner.nextLine();
		displayMenu();
		String ch=scanner.nextLine().toUpperCase();
		while(ch.equals("R")) {
		currentDice=rollDice(currentDice);
		System.out.println("Your Starting dice:"+currentDice);
		changeDice(currentDice);
     	System.out.println(currentDice);
     	displayMenu();
     	ch=scanner.nextLine().toUpperCase();
		}
		System.out.println();
		
	}

}