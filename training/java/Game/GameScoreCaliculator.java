package Game;

public class GameScoreCaliculator {
	
	public static void main(String [] args)
	{
		int score=10_000,levelCompleted=8,bonus=200;
		boolean gameOver=true;
		int finalScore= score + levelCompleted * bonus;
		if(gameOver)
		{
			System.out.print("finalScore of the plauer is  "+ finalScore);
		}
	}

}
