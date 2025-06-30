import java.util.Scanner;

public class Package {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter the values:");
		int big=sc.nextInt();
		int small=sc.nextInt();
		int capacity=sc.nextInt();
		canPack(big,small,capacity);
		

	}
	public static void canPack(int big,int small,int capacity) {
		if(big<0 || small<0 || capacity<0) {
			System.out.println("Invalid");
		}
		else {
			int maxiBig = capacity/5;
			if(maxiBig>big) {
				maxiBig=big;
			}
			int remaining=capacity-(5*maxiBig);
			if(remaining <= small) {
				System.out.println("true");
			}
			else {
				System.out.println("false");
			}
		}
		
		
	}

}
