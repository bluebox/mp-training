package day6.equalUpToThreeDecimal;
import java.util.*;

public class EqualUpToThreeDecimal {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		EqualDouble e=new EqualDouble();
		double inp1,inp2;
		System.out.println("Enter two double typed numbers");
		while(true) {
			inp1=sc.nextDouble();
			if(inp1==-1.0)
				break;
			inp2=sc.nextDouble();
			
			System.out.println(e.equalUpToThreeDecimal(inp1,inp2));
			System.out.println("-------------------------------");
			
			
			
		}
	}
}
