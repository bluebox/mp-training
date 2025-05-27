
import java.util.*;
public class SpeedConverter {
    public static double toMilesPerHour(double kiloMeters) {
    	if(kiloMeters<0)return -1;
    	double miles=kiloMeters/1.6;
    	return miles;
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int kiloMeter=sc.nextInt();
		System.out.println(toMilesPerHour(kiloMeter));
	}
}
