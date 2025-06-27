package com.Day1_Premitive_Types_and_Operators_and_Methods;

public class Methods {
	public static void main (String[]args) {
		boolean gameover = true;
		int score = 10000;
		int levelcompleted = 8;
		int bonus = 200;
		int finalscore = score;
		if (gameover) {
			finalscore = score += (levelcompleted*bonus);
			System.out.println("Your Final Score is: " + finalscore);
		}
		
		
	}

}
