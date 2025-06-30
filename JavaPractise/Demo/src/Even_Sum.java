import java.util.Scanner;

public class Even_Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the number:");
		String number=sc.nextLine();
		int num=Integer.parseInt(number);
		int sum=0;
		while(num!=0) {
			int digit=num%10;
			if(digit%2==0) sum+=digit;
			num=num/10;
		}
		System.out.println(sum);

	}

}
