package conditions;
public class CodingChallenge {

	public static void main(String[] args) {
		int score = 10000;
		int levelCompleted = 8;
		int bonus = 800;
		boolean gameOver = true;
		if(gameOver == true)
		{
			System.out.println("Total Score :: "+(score+(levelCompleted*bonus)));
		}
	}

}
