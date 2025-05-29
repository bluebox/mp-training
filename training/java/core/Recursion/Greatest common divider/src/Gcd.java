import java.util.Scanner;

public class Gcd {
	public static int gcd(int x,int y) {
		if((x%y)==0) {
			return y;
		}
		else {
			int temp=(x%y);
			return gcd(y,temp);
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int y=sc.nextInt();
		System.out.print(x>y?"Gcd of "+x+","+y+" is "+gcd(x,y):"Gcd of "+x+","+y+" is "+gcd(x,y));
	}
}
