package com.medplus;

public class MethodChallenge {
	public void displayHighScorePosition(String Name , int Position) {
		System.out.println(Name +" Managed to get into positon "+Position+" on the high Score list");
	}
	public int calculateHighScorePosition(int Score) {
		if (Score >= 1000) return 1;
		else if (Score >= 500) return 2;
		else if (Score >=100) return 3;
		else return 4;
	}
	public static void main(String[] args) {
		MethodChallenge position = new MethodChallenge();
		position.displayHighScorePosition("Tim",2);
		System.out.println("The man Scored 750 is Postion at "+position.calculateHighScorePosition(750));
	}
}
