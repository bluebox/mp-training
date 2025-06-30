import java.util.Scanner;

public class Sum_First_Last {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the number:");
		String num=sc.nextLine();
		int size=num.length();
		int sum=Integer.parseInt(""+num.charAt(0)) + Integer.parseInt(""+num.charAt(size-1));
		System.out.println(sum);

	}

}
