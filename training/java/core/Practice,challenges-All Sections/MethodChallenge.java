
public class MethodChallenge {

	public static void displayHighScorePosition(String playerName,int position)
	{
		System.out.println(playerName+" managed to get into position "+ position + " on the high scorelist");
	}
	public static void main(String[] args)
	{
		displayHighScorePosition("surya", calculateHighScorePosition(1500));
		displayHighScorePosition("chaitanya", calculateHighScorePosition(1000));

		displayHighScorePosition("bhargav", calculateHighScorePosition(500));
		displayHighScorePosition("sathwik", calculateHighScorePosition(100));
		displayHighScorePosition("audrey", calculateHighScorePosition(25));


	}
	public static int calculateHighScorePosition(int playerScore)
	{
		if(playerScore>=1000)
			return 1;
		if(playerScore >= 500)
			return 2;
		if(playerScore >= 100)
			return 3;
		return 4;
	}
	
}
	