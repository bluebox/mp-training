package fundamentals;

public class ifelse {
	public static void main(String args[])
	{
		int score=10000;
		int levelCompleted=8;
		int bonus=200;
		boolean gameOver=true;
		if(gameOver)
		{
			int finalScore=score+(levelCompleted*bonus);
			System.out.print(finalScore);
		}
	}
}
