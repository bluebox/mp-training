package challenge;

public class Main {
	public static void main(String[] args) {
		System.out.println(getDurationString(3600));
		System.out.println(getDurationString(125,34));

		
	}
	public static String getDurationString(int seconds)
	{
		return getDurationString(seconds/60,seconds%60);
		
	}
	public static String getDurationString(int minutes,int seconds)
	{
		int hours=minutes/60;
		minutes=minutes%60; 
		return String.format("%2dh %2dm %2ds",hours,minutes,seconds);
		
	}

}
