package LinkedListChallenge;
import java.util.Scanner;
import java.util.ListIterator;
public class Main 
{
	private static void menuProgram(HandlingItinerary handle)
	{
		displayMenu();
		Scanner input = new Scanner(System.in);
		int iterator = 0;
		System.out.println("Journey starting point: " + handle.getTowns().get(0).getTown());
		
		
		boolean exitStatus = false;
		
		while(!exitStatus)
		{
			System.out.print("Enter your choice: ");
			String userInput = input.nextLine().toLowerCase();
			
			switch(userInput)
			{
				case "f", "forward" ->
				{
					if(iterator < handle.getTowns().size()-1)
					{
						iterator++;
						System.out.println("Going to visit the next town: " + handle.getTowns().get(iterator).getTown());
					}
					else
					{
						System.out.println("No town ahead to visit.");
					}
					
				}
				case "b", "backward" ->
				{
					if(iterator > 0)
					{
						iterator--;
						System.out.println("Going to visit the previous town: " + handle.getTowns().get(iterator).getTown());
					}
					else
					{
						System.out.println("You have reached the first starting town in the itinerary.");
					}
				}
				case "l", "list" ->
				{
					for(int  i = 0; i< handle.getTowns().size(); i++)
					{
						System.out.println(" - " + handle.getTowns().get(i).getTown());
					}
				}
				case "m", "menu" ->
				{
					displayMenu();
				}
				case "q", "quit" ->
				{
					exitStatus = true;
				}
			}
		}
		input.close();
		
	}
	private static void displayMenu()
	{
        System.out.println("\nAvailable actions (select word or letter):");
        System.out.println("(F)orward");
        System.out.println("(B)ackward");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(Q)uit");
    }
	public static void main(String [] args)
	{
		HandlingItinerary handle = new HandlingItinerary();
		
		handle.insertNewTown(new SydneyToTowns("Sydney", 0));
		handle.insertNewTown(new SydneyToTowns("Adelaide", 1374));
		handle.insertNewTown(new SydneyToTowns("Alice Springs", 2771));
		handle.insertNewTown(new SydneyToTowns("Brisbane", 917));
;		menuProgram(handle);
	}
}
