package day6.equalSum;
import java.util.*;

public class EqualSum {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		HasEqualSum s=new HasEqualSum();
		int t1,t2,t3;
		System.out.println("Enter three numbers and -1 to quit");
		while(true) {
			try {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			t2=sc.nextInt();
			t3=sc.nextInt();
			System.out.println(s.hasEqualSum(t1,t2,t3));
			System.out.println("----------------------");
			}
			catch(Exception e) {
				System.out.println("Invalid inputs not allowd ");
				break;
			}
			
		}
	}
}
