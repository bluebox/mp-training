import java.util.Scanner;

public class Calculate_Intrest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the amount :");
		double amount=sc.nextDouble();
		double ans=0d;
		ans=amount+(0.25*amount)/100;
		System.out.println("The Total amount to be paid is:"+ ans);

	}

}
