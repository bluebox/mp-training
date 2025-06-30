import java.util.Scanner;

public class GCD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Eneter the number:");
		int a=sc.nextInt();
		int b=sc.nextInt();
		while(a>0 && b>0) {
			if(a>b) a=a%b;
			else b=b%a;
		}
		if(a==0) System.out.println("The GCD is :"+b);
		else System.out.println("The GCD is :"+a);

	}

}
