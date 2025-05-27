import java.util.*;
public class FactorsOfANumber {
	public static void printFactors(int num) {
		for(int i=1;i<=num;i++) {
			if(num%i==0) {
				System.out.print(i+" ");
			}
		}
	}
	public static void perfectNumber(int num) {
		if(num <0) return ;
		int sum=0;
		for(int i=1;i<num;i++) {
			if(num%i==0) {
				System.out.print(i+" ");
				sum+=i;
			}
		}
		if(sum==num) {
			System.out.print("Perfect Number");
		}else {
			System.out.print("Not Perfect Number");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		printFactors(num);
	}

}
