import java.util.Scanner;

public class Min_Max {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int max=0,min=0;
		while(true) {
			System.out.println("Enter the numbers:");
			try {
				int num= sc.nextInt();
				if(num>max) max=num;
				if(num<min) min=num;
			}
			catch(Exception e) {
				System.out.println("Maximum and Minimum values in your input are :"+max+" and "+min);
				break;
			}
		}

	}

}
