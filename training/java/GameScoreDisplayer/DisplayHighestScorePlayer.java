package GameScoreDisplayer;

public class DisplayHighestScorePlayer {

	public static void main(String [] args)
	{
		int score=1500;
		String name="ramsai";
		dispayHighScorePosition(score,name);
	}
	
	public static void dispayHighScorePosition(int score,String name)
	{
		
		System.out.println(name +" managed to get the "+calculateHighScorePosition(score)+ " postion");
	}
	public static int calculateHighScorePosition(int score)
	{
		int position=4;
		if(score >= 1000)
			position=1;
		else if(score >= 500 && score < 1000)
			position=2;
		else if(score >= 100 && score < 500)
			position=3;
		return position;
	}
}
