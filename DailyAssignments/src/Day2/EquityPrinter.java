package Day2;

public class EquityPrinter {

	public static void main(String[] args) {
		printEqual(5,1,-1);

	}
	public static void printEqual(int n1,int n2,int n3) {
		if((n1<0) ||(n2<0) || (n3<0)) {
			System.out.println("invalid numbers are entered");
		}else if(n1 ==n2 && n2==n3) {
			System.out.println("all numbers are equal");
		}else if(n1!=n2 && n1!=n3) {
			System.out.println("all numbers are different");
		}else {
			System.out.println("neither all are equal or different");
		}
	}

}
