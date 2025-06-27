
public class Example8 {

	public static void calculateScore(boolean gameOver,int points,int coins)
	{
		if(gameOver)
		{
		int ans=(coins*10)+points;
		System.out.println("Total Score is"+ans);
		}
		else
		{
			System.out.println("Your game is not over");
		}
	}
	public static void main(String[] args) {
		
		calculateScore(true,1000,10);
		calculateScore(false,1000,10);

	}

}
