import java.util.Scanner;
public class Calculator {
	public static void printSumAndAverage() {
		System.out.println("enter input");
		Scanner scanner=new Scanner(System.in);
		int sum=0;
		int count=0;
		while(true) {
			if(scanner.hasNextInt()) {
				int number=scanner.nextInt();
				sum+=number;
				count++;
			}else {
				break;
			}
		}
		long average=(count==0)?0:Math.round((double)sum/count);
		
		System.out.println("sum= "+sum+"avg = "+average);
		scanner.close();
	}
}
