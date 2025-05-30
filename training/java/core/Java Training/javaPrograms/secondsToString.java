package javaPrograms;

public class secondsToString {
	
	public static void getTimeFormate(int seconds)
	{
		int hours=seconds/(60*60);
		int minutes=(seconds%3600)/60;
		int remSeconds=(seconds%(60));
		System.out.println(seconds+" 's time formate : "+hours+"h "+ minutes + "m "+remSeconds+"s");
	}
	public static void getTimeFormate( int minutes,int seconds)
	{
		seconds+=(minutes*60);
		long hours=seconds/(60*60);
		long remMinutes=(seconds%3600)/60;
		long remSeconds=(seconds%(60));
		System.out.println(minutes +" 'm and "+seconds+" 's time formate : "+hours+"h "+ remMinutes + "m "+remSeconds+"s");
	}
	
	public static void main(String[] args) {
		getTimeFormate(8796);
		getTimeFormate(234,459898);

	}
}
