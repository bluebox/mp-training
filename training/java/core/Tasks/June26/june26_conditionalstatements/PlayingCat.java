package corejava.june26_conditionalstatements;

import java.util.Scanner;

public class PlayingCat {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter true if it is summer else false");
		boolean summer=sc.nextBoolean();
		System.out.println("Enter the temperature");
		int temperature=sc.nextInt();
		boolean isPlaying=isCatPlaying(summer,temperature);
		if (isPlaying)
			System.out.println("The cat is playing outside");
		else 
			System.out.println("The cat is not playing outside");
		sc.close();
	}
	public static boolean isCatPlaying(boolean summer,int temperature) {
		if(summer && 25<=temperature && temperature<=45) {
			return true;
		}
		else if(temperature>=25 && temperature<=35) {
			return true;
		}
		return false;
	}
}