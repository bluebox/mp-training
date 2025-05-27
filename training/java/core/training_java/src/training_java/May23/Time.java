package training_java.May23;

public class Time {
public static String getDuration(int seconds) {
	int minutes=seconds/60;
	seconds=seconds%60;
	return getDuration(minutes,seconds);
	
}
public static String getDuration(int minutes,int seconds) {
	int hours=minutes/60;
	minutes=minutes%60;
	return hours + "h"+minutes+"m"+seconds+"s";
	
}
public static void main(String[] args) {
	System.out.println(getDuration(300));
}

}
