import java.util.Scanner;

public class Palindrome {
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int z=x;
		int y=0;
		while(x!=0) {
			y=(y*10)+(x%10);
			x/=10;
		}
		if(z==y) {
			System.out.println(y+" is palindrome");
		}
		else {
			System.out.println(y+" is not palindrome");
		}
		sc.close();
	}
}
