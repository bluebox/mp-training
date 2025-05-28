import java.util.*;
public class ValidUserInput {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int c=1;
		do {
			try {
				System.out.print("Enter number #"+c+":");
				double num=sc.nextDouble();
				c++;
			}catch(InputMismatchException e) {
				System.out.println("Invalid number value :" +sc.next());
			}
		}while(c<=n);
		
	}

}
