
public class PrintEqual {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		print_Equal(1,1,1);
		print_Equal(1,1,2);
		print_Equal(-1,-1,-1);
		print_Equal(1,2,3);
		

	}
	public static void print_Equal(int a,int b, int c) {
		if(a<0 || b<0 || c<0) {
			System.out.println("Invalid Input");
		}
		else if(a==b && b==c) {
			System.out.println("All numbers are equal");
		}
		else if(a!=b  && b!=c && a!=c) {
			System.out.println("All numbers are different");
		}
		else {
			System.out.println("Neither all are equal or different");
		}
	}

}
