import java.util.Scanner;

public class MultipeSum {
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		int count=sc.nextInt();
		int sum=(60*(count/7));
		int i=3+(15*(count/7));
		count%=7;
		while(count!=0) {
			if(((i%3)==0)||((i%5)==0)) {
				sum+=i;
				System.out.println(sum+" "+count);
				count-=1;
			}
			i+=1;
		}
		System.out.print("Sum of first five number that are multiples of 3 or 5 is "+sum);
	}
}
