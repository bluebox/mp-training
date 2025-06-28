import java.util.Scanner;

public class SumOfOdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter Start and End numbers: ");
		int start=sc.nextInt(),end=sc.nextInt();
		
		int result=addOdd(start,end);
		
		if(result!=-1)
			System.out.println("Sum of the Odd Numbers in given range: "+ addOdd(start,end));
		else
			System.out.println("-1");
		

	}
	
	public static int addOdd(int start,int end) {
		
		int sum=0;
		
		if(start<0 || end<0) {
			return -1;
		}
		
		for(int i=start;i<=end;i++) {
			if(isOdd(i)) {
				sum+=i;
			}
		}
		
		return sum;
	}
	
	public static boolean isOdd(int num) {
		
		if(num<0) {
			return false;
		}else if(num%2==0) {
			return false;
		}
		return true;
	}
}
