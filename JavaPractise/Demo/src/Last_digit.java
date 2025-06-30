import java.util.Scanner;

public class Last_digit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the valuse:");
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		boolean a1=isValid(a);
		boolean b1=isValid(b);
		boolean c1=isValid(c);
		if(a1==true && b1==true && c1==true) {
			System.out.println("True");
		}
		else {
			System.out.println("False");
		}

	}
	public static boolean isValid(int num) {
		if(num>=10 && num<=999) {
			return true;
		}
		return false;
	}

}
