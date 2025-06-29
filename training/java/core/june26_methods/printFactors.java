package june26_methods;
import java.util.Scanner;
public class printFactors {
	public static void printFactor(int num) {
		if (num<1)
			System.out.println("Invalid number");
		else {
			for(int i=1;i<=num;i++) {
				if(num%i==0) {
					System.out.println(i);}
				
			}
	}}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number : ");
		int num=sc.nextInt();
		printFactor(num);
		sc.close();
	}

}
