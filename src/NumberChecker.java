import java.util.Scanner;

public class NumberChecker {
	public static boolean hasTeen(int a,int b,int c) {
		if((a>=13 && a<=19) || (b>=13 && b<=19) || (c>=13 && c<=19)) return true;
		return false;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		int c=sc.nextInt();
		boolean result=hasTeen(a,b,c);
		System.out.print(result);

	}

}
