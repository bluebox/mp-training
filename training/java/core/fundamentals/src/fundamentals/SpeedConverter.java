package fundamentals;

public class SpeedConverter {
	public static void main(String args[])
	{
		System.out.println(10.25+" Kmph is: "+toMilePerHour(10.25)+".");
		System.out.println(-5.6+" Kmph is: "+toMilePerHour(-5.6)+".");
		System.out.println(75.114+" Kmph is: "+toMilePerHour(75.114)+".");
		
		toMilePerHour2(10.25);
		toMilePerHour2(-5.6);
		toMilePerHour2(75.114);
		
	}
	static long toMilePerHour(double kiloMeterPerHour)
	{
		if(kiloMeterPerHour<0)
			return -1;
		long ans=(long)(kiloMeterPerHour*0.621371);
		return ans;
	}
	static void toMilePerHour2(double kiloMeterPerHour)
	{
		if(kiloMeterPerHour<0)
			System.out.println("Invalid input.");
		long ans=(long)(kiloMeterPerHour*0.621371);
		System.out.println(kiloMeterPerHour +"km/h = "+ans);
	}
}
