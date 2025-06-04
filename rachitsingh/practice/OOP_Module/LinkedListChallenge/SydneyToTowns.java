package LinkedListChallenge;

public class SydneyToTowns
{
	private String town;
	private int distanceFromSydney;
	
	public SydneyToTowns(String town, int distanceFromSydney)
	{
		this.town = town;
		this.distanceFromSydney = distanceFromSydney;
	}
	
	public String getTown()
	{
		return town;
	}
	
	public void setTown(String town)
	{
		if(!town.isBlank())
		{
			this.town = town;
		}
	}
	
	public int getDistanceFromSydney()
	{
		return distanceFromSydney;
	}
	public void setDistanceFromSydney(int distanceFromSydney)
	{
		if(distanceFromSydney >= 0)
		{
			this.distanceFromSydney = distanceFromSydney;
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
