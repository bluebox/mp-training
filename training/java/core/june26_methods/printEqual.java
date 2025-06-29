package june26_methods;
import java.util.Scanner;
public class printEqual {
	
	public static void printEqual(int a,int b,int c) {
		if(a<0 || b<0 || c<0)
			System.out.println("Invalid Values");
		else if (a==b && b==c)

			System.out.println("All numbers are equal");
		else
			System.out.println("Neither all are equal or different");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 3 numbers ");
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		sc.close();
		printEqual(a,b,c);

	}

}
