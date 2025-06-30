package corejava.june25_Ifelse;

public class ScoreCheck {

	public static void main(String[] args) {
		int Score=10_000;
		int levelCompleted=8;
		int bonus=200;
		boolean gameOver=true;
		int finalscore =Score;
		if(gameOver) {
			finalscore+=(levelCompleted*bonus);
			System.out.println("Your Final Score is:" + finalscore);
		}
		else {
			System.out.print("Your Final Score is:" + finalscore);
		}
		
	}

}
