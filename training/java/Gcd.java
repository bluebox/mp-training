import java.util.*;

public class Gcd {
	public static int gcd(int a,int b) {
		if(a==0) return b;
		if(b==0) return a;
		if(a>0 && b>0) {
			if(a>b) return gcd(a%b,b);
			return gcd(a,b%a);
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int res=gcd(a,b);
		System.out.print(res);
	}

}
