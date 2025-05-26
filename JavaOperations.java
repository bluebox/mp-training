package Main;

import java.util.*;
public class JavaOperations {
	public static int printEvenNumbers(int start,int end) {
		int evenNumbersCount=0;
		System.out.println("Even numbers in the range are:");
		for(int i=start;i<=end;i++) {
			if ( i%2 == 0 ) {
				System.out.print(i+" ");
				evenNumbersCount++;
				
			}
				
		}
		System.out.println();
		return evenNumbersCount;
	}
	public static void main(String args[]) {
		
		Scanner sc=new Scanner(System.in);
		
		int start=sc.nextInt();
		System.out.println("Enter ending Numbers of range.");
		int end=sc.nextInt();
		int count=printEvenNumbers(start,end);
		System.out.println("Number of Even Numbers between "+start+" and "+end+" are "+count);
		
		sc.close();
	}

}
