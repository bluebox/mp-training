package SpeedConverter;

public class KilometersToMiles {

	public static void main(String[] args) {
		

		double kilometerPerHour = 0.25;
		printConversion(kilometerPerHour);
		
	}
	public static long toMilePerHour(double kilometerPerHour)
	{
		if(kilometerPerHour < 0 )
			return -1;
		
		return (long) Math.round(kilometerPerHour * 0.621371);
	}
	
	public static void printConversion(double kilometerPerHour)
	{
		long convertedResult = toMilePerHour(kilometerPerHour);
		System.out.println( kilometerPerHour+" km/h =  "+ convertedResult +" mi/h");
		
	}

}
