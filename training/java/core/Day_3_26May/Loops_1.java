package Day_3_26May;

import java.util.Scanner;

public class Loops_1 {
		public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			int start=sc.nextInt();
			int end=sc.nextInt();
			int sum=0;
			for(int i=start;i<end;i++) {
				if(i%3==0 && i%5==0) {
					sum+=i;
				}
			}
			System.out.println("Sum of digits divisible by 3 and 5 between specified ranges is: "+sum);
		}

}
