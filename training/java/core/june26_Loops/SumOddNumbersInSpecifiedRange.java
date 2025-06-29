package june26_Loops;
import java.util.Scanner;
public class SumOddNumbersInSpecifiedRange {
	public static boolean isOdd(int num) {
		if(num<0)
			return false;
		return (num%2!=0);
	}
	public static int sumOdd(int start,int end) {
		int sum=0;
		for(int i=start;i<=end;i+=1) {
			if(isOdd(i))
				sum+=i;}
		return sum;
	
}	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the numbers range");
		int a=sc.nextInt();
		int b=sc.nextInt();
		int result=-1;
		if (a>0 && b>0) {
			result=sumOdd(a,b);
			System.out.println("The Sum of Odd Numbers between "+a+" and "+b+" : "+result);}
		else
			System.out.println("-1");
		sc.close();
	

}}
