import java.util.*;
public class Factor {
	static void factor(int num) {
		factor(1,num);
	}
	static void factor(int n,int num) {
		
		if((n*n)>num) {
			return;
		}
		else if((num%n)==0) {
			System.out.println(n);
			factor(n+1,num);
			System.out.println(num/n);
		}
		else {
			factor(n+1,num);
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number:");
		factor(sc.nextInt());
		sc.close();
	}
}
