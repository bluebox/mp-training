
public class if_then_Challenge {

	public static void main(String[] args) {
		boolean gameOver = true;
		int score = 800;
		int levels_Completed = 5;
		int bonus =100;
		int final_Score = 0;
		if(gameOver) {
			final_Score = score + (levels_Completed*bonus);
			System.out.println("Final Score is :"+ final_Score);
		}

	}

}
