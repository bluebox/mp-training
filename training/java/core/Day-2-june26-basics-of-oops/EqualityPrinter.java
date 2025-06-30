package day2;

public class EqualityPrinter {

	public static void main(String[] args) {
		printEqual(1,1,1);
		printEqual(1,1,2);
		printEqual(-1,-1,-1);
		printEqual(1,2,3);
	}
	public static void printEqual(int a, int b,int c) {
		if (a<0||b<0||c<0) {
			System.out.println("Invalid Value");
		}
		else if(a==b &&a==c) {
			System.out.println("All are equal");
		}
		else if(a!=b && a!=c &&b!=c) {
			System.out.println("All are different");
		}
		else {
			System.out.println("Neither all different or equal");
		}
	}
}
