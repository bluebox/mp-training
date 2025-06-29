package june26_methods;
import java.util.Scanner;
public class SumfirstAndLast {
	
	public static void sumFirstAndLast(int num) {
		String s=Integer.toString(num);
		System.out.println(s);
		int f=(s.charAt(0))-'0';
		int l=(s.charAt(s.length()-1))-'0';
		System.out.println(f);
		System.out.println("Sum of first and last digits of a number : "+(f+l)); 
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number : ");
		int num=sc.nextInt();
		sumFirstAndLast(num);
		sc.close();
	}

}
