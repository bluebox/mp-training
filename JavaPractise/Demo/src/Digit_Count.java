import java.util.Scanner;

public class Digit_Count {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number:");
		int num=sc.nextInt();
		if(num<0) {
			System.out.println("-1");
		}
		else {
			int count=0;
			while(num!=0) {
				count++;
				num=num/10;
				
			}
			System.out.println(count);
		}
	}

}
