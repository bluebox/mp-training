package June26;

import java.util.Scanner;

public class SumAndAvg {
	public static void main(String[] args) {
		int i = 0;
		int sum = 0, avg = 0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("Enter a number (or any non-number to stop): ");
			String s = sc.nextLine();
			try {
				int validNum = Integer.parseInt(s);
				sum += validNum;
				i++;
			}catch(NumberFormatException e) {
				break;
			}
		}
		if(i > 0) avg = sum / i;
		System.out.println("Sum = " + sum + " Avg = " + avg);
		sc.close();
	}
}
