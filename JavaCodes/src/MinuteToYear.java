import java.util.*;
public class MinuteToYear {
    public static void printYearAndDays(long minutes) {
    	long totalMinutesInYear=(24*60*365);
    	long year=minutes/totalMinutesInYear;
    	long remainginDays=minutes%totalMinutesInYear;
    	System.out.print(minutes+" min ="+year+" y "+(remainginDays)/(24*60)+"d");
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		long minutes=sc.nextLong();
		printYearAndDays(minutes);
	}

}
