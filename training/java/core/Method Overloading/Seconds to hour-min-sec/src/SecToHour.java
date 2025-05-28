import java.util.Scanner;

public class SecToHour {
	static String secToHour(int sec) {
		if(sec<60) {
			return sec+" seconds";
		}
		else if(sec<3600) {
			return (sec/60)+" minutes "+(sec%60)+" seconds";
		}
		else {
			return secToHour(sec/60,sec%60);
		}
	}
	
	
	static String secToHour(int min,int sec) {
		if(sec>60) {
			min+=(sec/60);
			sec=sec%60;
		}
		if(min>60) {
			return (min/60)+" hours "+(min%60)+" minutes "+sec+" seconds";
		}
		else {
			return (min%60)+" minutes "+sec+" seconds";
		}
	}
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println(secToHour(5440));
		System.out.println(secToHour(154,40));
		System.out.println(secToHour(545,440));
	}
}
