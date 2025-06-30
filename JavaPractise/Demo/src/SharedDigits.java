import java.util.Scanner;

public class SharedDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Eneter the numbers:");
		int a=sc.nextInt();
		int b=sc.nextInt();
		if(a>=10 && b<=99) {
			System.out.println("True");
		}
		else {
			System.out.println("False");
		}

	}

}
