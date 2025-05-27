import java.util.*;
public class MinutesToYearsAndDays {
	public static String printYearsAndDays(long minutes) {
		int years=(int) (minutes/(365*24*60));
		long min=minutes;
		min=minutes%(365*24*60);
		int days=(int) (min/(24*60));
		return minutes+" min = "+years+" y and "+days+" d";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner (System.in);
		long minutes=sc.nextLong();
		String result=printYearsAndDays(minutes);
		System.out.print(result);

	}

}
