import java.util.*;
public class SecondsAndMinutes {
	public static String secondFunction(int totalSecond) {
		int totalHours=totalSecond/3600;
		int minutes=(totalSecond%3600)/60;
		int sec=(totalSecond%3600)%60;
		return totalHours+"h"+minutes+"m"+sec+"s";
	}
    public static String secondAndMinutes(int minutes,int seconds) {
    	int totalSecond=(60*minutes)+seconds;
    	return secondFunction(totalSecond);
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int minutes=sc.nextInt();
		int second=sc.nextInt();
		System.out.print(secondAndMinutes(minutes,second));
		int seconds=sc.nextInt();
		System.out.print(secondFunction(seconds));
	}

}
