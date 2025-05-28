package samplecodes;

public class EqualityPrinter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printEqual(1,2,3);
	}
	public static void printEqual(int a,int b,int c) {
		if(a==b && b==c){
			System.out.println("All are Equal");
		}else if(a==b || b==c || a==c) {
			System.out.println("Neither all are equal or different");
		}else {
			System.out.println("All are Different");
		}
	}

}
