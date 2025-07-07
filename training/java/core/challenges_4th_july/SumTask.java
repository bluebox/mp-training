package challenges_4th_july;

import java.util.Scanner;

public class SumTask {
	
	public static int digitSum(int n) {
		 if (n == 0) {
	            return 0;
	        } else if (n % 9 == 0) {
	            return 9;
	        } else {
	            return n % 9;
	        }
    }

    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
    	int n=sc.nextInt();
//        int n = 12345;
        System.out.println(digitSum(n)); 
    }

}
