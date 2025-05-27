import java.util.Scanner;

public class DisplayPosition {
	static String displayHighScorePosition(String playerName, int position) {
		return playerName+" managed to get into position "+position+" on the high score list.";
	}
	static int calculateHighScorePosition(int playerScore) {
		if(playerScore >= 1000) return 1;
		if(playerScore >=500) return 2;
		if(playerScore >=100) return 3;
		return 4;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sci=new Scanner (System.in);
		String playerName=sci.nextLine();
		int score=sci.nextInt();
		int position=calculateHighScorePosition(score);
		String result=displayHighScorePosition(playerName, position);
		System.out.print(result);
	}

}
