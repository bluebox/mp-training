import java.util.Scanner;

public class User_Input {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int sum=0;
		for(int i=1;i<=5;i++) {
			System.out.println("Enter the number #:"+i+" :");
			try{
				int n=sc.nextInt();
				sum+=n;
			}
			catch(Exception e) {
				System.out.println("Invalid Input");
				sc.next();
				i--;
			}
			
		}
		System.out.println(sum);

	}

}
