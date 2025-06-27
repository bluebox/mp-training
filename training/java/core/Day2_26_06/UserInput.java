package Day2_26_06;
import java.util.Scanner;
public class UserInput {
	public static void main(String args[]) {
		int sum=0;
		for(int i=1;i<=5;i++) {
			try {
			System.out.println("Enter number #"+i);
			Scanner sc=new Scanner(System.in);
			String inp=sc.nextLine();
			int num=Integer.parseInt(inp);
			sum+=num;
			}catch(Exception e) {
				i--;
			}
		}
		System.out.println(sum);
	}
	
}
