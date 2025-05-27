import java.util.*;

public class SpeedConverter {
	public static double toMilesPerHour(double kilometersPerHour) {
		double miles=kilometersPerHour/1.609d;
		return miles;
	}
	public static String printConversion(double kilometersPerHour) {
		if(kilometersPerHour <0) return "Invalid value";
		long miles=Math.round(toMilesPerHour(kilometersPerHour));
		return kilometersPerHour+" km/h = "+miles+" ml/h";
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		double kmPerH=sc.nextDouble();
		String result=printConversion(kmPerH);
		System.out.print(result);
	}

}
