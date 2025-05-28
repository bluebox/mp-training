package TimeConversion;

public class SecondsToStringFormat {

	public static void main(String[] args) {
		int seconds=3661;
		getDurationString(seconds);
		getDurationString(234,459898);
		

	}
	public static void getDurationString(int seconds)
	{
		int hours=seconds/(60*60),minutes=(seconds%3600)/60,resSeconds=(seconds%(60));
		System.out.println(hours+"h "+ minutes + "m "+resSeconds+"s");
	}
	public static void getDurationString( int minutes,int seconds)
	{
		seconds+=(minutes*60);
		long hours=seconds/(60*60),remMinutes=(seconds%3600)/60,resSeconds=(seconds%(60));
		System.out.println(hours+"h "+ remMinutes + "m "+resSeconds+"s");
	}

}
