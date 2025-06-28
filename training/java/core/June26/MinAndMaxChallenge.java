package June26;
import java.util.Scanner;

public class MinAndMaxChallenge {
	public static void main(String[] args) {
		int lc = 0;
		double max = 0, min = 0;
		Scanner sc = new Scanner(System.in);
		while(true) {
			String s = sc.nextLine();
			try {
				double validNum = Double.parseDouble(s);
				if(lc == 0 || validNum < min) min = validNum;
				if(lc == 0 || validNum > max) max = validNum;
				lc++;
			}catch(NumberFormatException e) {
				break;
			}
		}
		if(lc > 0) System.out.println("Minimum Value = " + min + " Maximum Value = " + max);
		else System.out.println("No valid data entered");
		sc.close();
	}
}
