package Main;

import java.util.Scanner;

public class SwitchExample1 {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int switchValue = scanner.nextInt();
		switch(switchValue) {
			case 1:System.out.println("Monday");
			   break;
			case 2:System.out.println("Tuesday");
			   break;
			case 3:System.out.println("Wednesday");
			   break;
			case 4:System.out.println("Thursday");
			   break;
			case 5:System.out.println("Friday");
			   break;
			case 6:System.out.println("Saturday");
			   break;
			case 7:System.out.println("Sunday");
			   break;
			default:System.out.println("Invalid value. Enter number from 1 to 7");
			   break;
		}
		scanner.close();
	}
}
