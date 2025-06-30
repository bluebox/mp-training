
public class Seconds_Minutes_Challenge {

	public static void main(String[] args) {
		
		print_Time(5000);
		print_Time(-2);
		print_Time(300,320);
		print_Time(120,5000);
		

	}
	public static void print_Time(int seconds) {
		if(seconds<0) {
			System.out.println("Invalid Input of seconds");
		}
		else {
			int minutes = seconds/60;
			seconds=seconds%60;
			print_Time(minutes,seconds);
		}
	}
	public static void print_Time(int minutes, int seconds) {
		if(minutes<0) {
			System.out.println("Invalid  minutes Input");
		}
		else if(seconds<0) {
			System.out.println("Invalid seconds  input");
		}
		else {
			minutes+=seconds/60;
			int hours = minutes/60;
			minutes = minutes%60;
			seconds=seconds%60;
			System.out.println("Time is :"+ hours +" hours "+minutes +" minutes "+seconds+" seconds");
		}
	}

}
