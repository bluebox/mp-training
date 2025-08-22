import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
public class Dice {
	static Random r=new Random();
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer>li=new ArrayList<>();
		int rolls=0;
		do
		{
			rollDice(li);
			li.clear();
		}while(!pickLosers(li));
		System.out.println("Game Over.Real game would score and continue.");
	}
	public static void rollDice(List<Integer> li)
	{
		int randomCount=5-li.size();
		List<Integer>li1=r.ints(randomCount,1,7)
									.sorted()
									.boxed()
									.toList();
		li.addAll(li1);
		System.out.println("You 're dice are: "+li);
				
	}
	private static boolean pickLosers(List<Integer>li)
	{
		String prompt="""
				Press Enter to Score.
				Type "All" to re-roll all the dice.
				List numbers (separated by spaces) to re-roll selected dice.
				""";
		System.out.print(prompt+ "----> ");
		String userInput=sc.nextLine();
		if(userInput.isBlank())
		{
			return true;
		}
		return false;	
	}
}