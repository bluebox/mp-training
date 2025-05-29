import java.util.Scanner;

public class PerfectNumber {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int x=sc.nextInt();
		int count=0;
		for(int i=1;(i*i)<=x;i++) {
			if((x%i)==0) {
				count+=(i+(x/i));
			}
		}
		System.out.println((x*2)==count?"Perfect number":"Not a perfect number");
	}
}
