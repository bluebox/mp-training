package LinkedListChallenge;
import java.util.LinkedList;

public class HandlingItinerary
{
	private LinkedList<SydneyToTowns> towns;
	
	public HandlingItinerary()
	{
		towns = new LinkedList<>();
	}
	
	public void insertNewTown(SydneyToTowns newTown)
	{
		for(int i = 0; i<towns.size(); i++)
		{
			SydneyToTowns currentTown = towns.get(i);
			if(currentTown.equals(newTown))
			{
				System.out.println("This town has already been included in the itinerary.");
				return;
			}
		}
		
		int index = 0;
		while(index < towns.size())
		{
			if(newTown.getDistanceFromSydney() > towns.get(index).getDistanceFromSydney())
			{
				index++;
			}
			else
			{
				break;
			}
		}
		towns.add(index, newTown);
		
	}
	
	public void displayItinerary()
	{
		System.out.println("Itinerary: ");
		for(int i = 0; i < towns.size(); i++)
		{
			SydneyToTowns currentTown = towns.get(i);
			System.out.println(" - " + currentTown);
		}
	}
	public LinkedList <SydneyToTowns> getTowns()
	{
		return towns;
	}
}
