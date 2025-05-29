import java.util.*;
public class EvenDigitSum {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int y=x;
		int count=0;
		while(x!=0) {
			if(((x%10)%2)==0) {
				count+=(x%10);
			}
			x/=10;
		}
		System.out.println("The sum of even digits in "+y+"="+count);
	}
}
