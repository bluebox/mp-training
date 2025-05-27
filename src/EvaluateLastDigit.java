import java.util.*;
public class EvaluateLastDigit {
	public static boolean hasSameLastDigit(int a,int b,int c) {
		if((a>=10 && a<=1000) && (b>=10 && b<=1000) && (c>=10 && c<=1000)) {
			int a1=a%10, b1=b%10, c1=c%10;
			if(a1==b1 || b1==c1 || a1==c1) return true;
			else return false;
		}
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		boolean result=hasSameLastDigit(a,b,c);
		System.out.print(result);
	}

}
