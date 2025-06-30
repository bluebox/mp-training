package corejava.june26_loops;

import java.util.Scanner;

public class ForLoopChallenge {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int primeCount=0;
		System.out.println("Enter a number from which prime number counting should be started(min:2)");
		int n1=sc.nextInt();
		System.out.println("Enter a number till which prime number counting should stop(max:1000)");
		int n2=sc.nextInt();
		if(n1>1 && n2<=1000) {
			if((n1&1) == 0) {
				primeCount=primeCountMethod(n1+1,n2);
				if(n1==2)
					primeCount++;
			}
			else {
				primeCount=primeCountMethod(n1,n2);
			}
			System.out.println("There are "+primeCount+" prime numbers in given range");
		}
		else {
			System.out.println("Enter a valid input range");
		}
		sc.close();
	}
	
	public static int primeCountMethod(int start,int end) {
		int flag=0;
		int count=0;
		for(int i=start;i<=end;i+=2) {
			flag=0;
			for(int j=2;j<=i/2;j++) {
				if(i%j==0) {
					flag=1;
					break;
				}	
			}
			if(flag==0)
				count++;
		}
		return count;
	}
}
