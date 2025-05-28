package samplecodes;

public class MethodChallenge {
	public static void main(String[] args) {
		int position=calculateHighScorePosition(125);
		displayHighScorePosition("Sameer",position);
	}
	public static void displayHighScorePosition(String playerName,int position) {
		System.out.println(playerName + " is managed to get into position "+ position+ " on the high score list");
	}
	public static int calculateHighScorePosition(int score) {
		if(score>=1000) return 1;
		else if(score >=500) return 2;
		else if(score >=100) return 3;
		return 4;
	}
}
