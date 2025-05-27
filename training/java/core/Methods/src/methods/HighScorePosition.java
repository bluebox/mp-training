package methods;
import java.util.*;
public class HighScorePosition {
	public static int calculatePosition(int playerScore)
	{
		if(playerScore>=1000)
		{
			return 1;
		}else if(playerScore>=500){
			return 2;
		}else if(playerScore>=100) {
			return 3;
		}else
		{
			return 4;
		}
	}
	
	public static void displayHighScorePosition(String playerName, int playerScore)
	{
		int playerPosition = calculatePosition(playerScore);
		System.out.println(playerName+" is at :: "+playerPosition);
	}
	public static void main(String args[]) {
		System.out.println();
		Scanner sc = new Scanner(System.in);
		String playerName = sc.nextLine(); 
		int playerScore = sc.nextInt();
		displayHighScorePosition(playerName, playerScore);
		
	}

}
