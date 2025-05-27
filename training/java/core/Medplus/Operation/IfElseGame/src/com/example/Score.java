package com.example;

public class Score {
	public static void displayHighScorePosition(String pName,int score) {
		if(score>1000) {
			System.out.println(pName+" managed to get into position 1 on the high score list");
		}
		else if(score>500) {
			System.out.println(pName+" managed to get into position 2 on the high score list");
		}
		else if(score>100) {
			System.out.println(pName+" managed to get into position 3 on the high score list");
		}
		else {
			System.out.println(pName+" managed to get into position 4 on the high score list");
		}
	}
	public static void main(String arg[]) {
		displayHighScorePosition("Ram",500);
		displayHighScorePosition("Shiva",300);
		displayHighScorePosition("Srinu",1500);
		displayHighScorePosition("Charle",800);
		displayHighScorePosition("Gopal",200);
		displayHighScorePosition("John",75);
		displayHighScorePosition("Ganesh",700);
	}
}
