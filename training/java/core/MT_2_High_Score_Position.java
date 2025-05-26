
public class MT_2_High_Score_Position {
	public static void main(String[] args) {
		int position=highScorePosition(9000);
		showHighScorePosition("Nagabhushan", position);
		
	}
	public static void showHighScorePosition(String name,int position) {
		System.out.println(name +" managed to get into postion "+position+" on high score list");
	}
	public static int highScorePosition(int score) {
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