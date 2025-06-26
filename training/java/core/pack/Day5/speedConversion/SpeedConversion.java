package Day5.speedConversion;
import java.util.*;

public class SpeedConversion {
public static void main(String args[]) {
	SpeedConverter sc=new SpeedConverter();
	Scanner s=new Scanner(System.in);
	int temp;
	long res;
	while(true) {
		temp=s.nextInt();
		if(temp==-1)
			break;
		
		sc.printConversion(temp);
		
		
	}
}
}
 class SpeedConverter {
    
    public static long toMilesPerHour(double kilometersPerHour) {
        return  Math.round(kilometersPerHour / 1.609D);
    }

    public static void printConversion(double kilometersPerHour) {
        if (kilometersPerHour < 0.0D) {
            System.out.println("Invalid Value");
        } else {
            long milesPerHour = toMilesPerHour(kilometersPerHour);
            System.out.println(kilometersPerHour + " km/h = " + milesPerHour + " mi/h");
        }
    }
}