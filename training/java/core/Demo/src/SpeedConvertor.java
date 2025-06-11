package ExpressionsStatementsMethodOverloading;
import java.util.Scanner;

public class SpeedConvertor {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		double kilometersPerHour= sc.nextDouble();
		System.out.println(toMilesPerHour(kilometersPerHour));
		
	}
	
	public static 
	
	public static long toMilesPerHour(double kilometersPerHour) {
		if(kilometersPerHour<0) return -1;
		long ans=Math.round (kilometersPerHour / 1.609);
		return ans;
		
	}

}
