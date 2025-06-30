import java.util.Scanner;

public class OddNumber_Range_Sum {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number to be checked:");
		int num=sc.nextInt();
		if(isOdd(num)) {
			System.out.println("Number is odd");
		}
		else {
			System.out.println("Number is even");
		}
		System.out.println("Enter the start and end range values:");
		int start=sc.nextInt();
		int end=sc.nextInt();
		System.out.println("The sum of odd numbers from "+start+" to "+end+" is :"+ sumOdd(start,end));

	}
	public static boolean isOdd(int num) {
		if(num<0) return false;
		if (num%2==1) return true;
		return false;
	}
	public static int sumOdd(int start, int end) {
		if(start==end && start%2==1) return start;
		if(start==end && start%2==0) return 0;
		if(start<0 || end<0) {
			return -1;
		}
		int sum=0;
		for(int i=start;i<=end;i++) {
			if(isOdd(i)) {
				sum+=i;
			}
		}
		return sum;
	}

}
