import java.util.Scanner;

public class EqualityCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Three Nums: ");
		int a=sc.nextInt(),b=sc.nextInt(),c=sc.nextInt();
		
		check(a,b,c);

	}
	
	public static void check(int a,int b,int c) {
		if(a==b && b==c && c==a) {
			System.out.println("All Nums are Equal");
		}else if(a<0 || b<0 || c<0) {
			System.out.println("Invalid values");
		}else if(a!=b && b!=c && c!=a) {
			System.out.println("All Nums are Different");
		}else {
			System.out.println("Neither all are equal or different");
		}
	}

}
