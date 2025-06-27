
public class YearsAndDaysChallenge {

	public static void printYearsAndDays(long minutes)
	{
		if(minutes<0)
		{
		System.out.println("Invalid Value");
		}
		else
		{
			long years=(minutes)/(525600);
			long remainingMinutes=minutes%525600;
			long days=(remainingMinutes)/1440;
			System.out.println(minutes+"min = "+ years+ "years"+ days +"days");
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printYearsAndDays(561600);

	}

}
