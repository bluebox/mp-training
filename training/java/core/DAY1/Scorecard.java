package day2;

public class Scorecard {
	public static void main(String[] args) {
		int pos=calculateHighScorePosition(9000);
		displayHighScorePosition("srisai", pos);
		System.out.println(pos);
	}
	public static void displayHighScorePosition(String name,int position) {
		System.out.println(name +" managed to get into postion "+position+" on high score list");
	}
	public static int calculateHighScorePosition(int score) {
		if(score>=1000) {
			return 1;
		}else if(score>=500) {
			return 2;
		}else if(score>=100) {
			return 3;
		}
		return 4;
	}
	
}
