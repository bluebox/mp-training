package dicePack;

import java.util.Arrays;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public  class Randomization {
	private static final int Dice_count=5;
	private static final int Least=1;
	private static final int Highest=6;
 public static void main(String []args)
 {
	 Scanner sc=new Scanner(System.in);
	 List<Integer> dice=rollAllDice();
	 while(true)
	 {
		 System.out.println("your dice are: "+dice);
		 System.out.println("Press enter to score");
		 System.out.println("Type ALL to re-roll ann the dice");
		 System.out.println("List numbers (saperated by spaces) to re-roll selected dice");
		 String input=sc.nextLine().trim();
		 if(input.isEmpty()) {
			 System.out.println("Game over real gave would score and continue");
			 break;
		 }
		 else if(input.equalsIgnoreCase("ALL"))
		 {
			 dice=rollAllDice();
		 }
		 else
		 {
			 List<Integer>valueToRoll=Arrays.stream(input.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
		     for (int val:valueToRoll)
		     {
		    	 for(int i=0;i<dice.size();i++)
		    	 {
		    		 if(dice.get(i)==val)
		    		 {
		    			 dice.set(i, rollDie());
		    			 break;
		    		 }
		    	 }
		     }
		 }
	 }
	 
 }
 private static int rollDie()
 {
	 return new Random().nextInt(Highest-Least+1)+Least;
 }
 private static List<Integer> rollAllDice()
 {
	 return new Random().ints(Dice_count,Least,Highest+1).boxed().collect(Collectors.toList());
 }
}
