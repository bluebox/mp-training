import java.util.Scanner;

public class IsPerfect_Number {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number:");
		int num=sc.nextInt();
		if(num<0) {
			System.out.println("false");
		}
		else {
			int sum=0;
			for(int i=1;i<num;i++) {
				if(num%i==0) {
					sum+=i;
				}
			}
			if(sum==num) System.out.println("true");
			else System.out.println("false");
		}

	}

}
