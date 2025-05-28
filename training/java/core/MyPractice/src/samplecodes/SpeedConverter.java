package samplecodes;

public class SpeedConverter {
	public static void main(String[] args) {
		printConversion(10.25);
	}
	public static long toMilesPerHour(double kiloMetersPerHour) {
		if(kiloMetersPerHour<0) return -1;
		double res=kiloMetersPerHour/1.609d;
		long ans=(long)Math.round(res);
		return ans;
	}
	public static void printConversion(double kiloMetersPerHour) {
		long ans=toMilesPerHour(kiloMetersPerHour);
		System.out.println(kiloMetersPerHour+"km/h = "+ans+"ml/h");
	}
}
