package june26_methods;
import java.util.Scanner;
public class GCD {
	public static int findGCD(int a,int b) {
		
		if(a<10 || b<10)
			return -1;
		//int gcd=0;
		else {
			while(b!=0) {
				int temp=b;
				b=a%b;
				a=temp;
			}
			return a;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter two numbers");
		int a=sc.nextInt();
		int b=sc.nextInt();
		int result=findGCD(a,b);
		if(result==-1)
			System.out.println("finding gcd is not possible for given numbers");
		else
			System.out.println("GCD of "+a+" and "+b+" : "+result);
		sc.close();
	}

}
