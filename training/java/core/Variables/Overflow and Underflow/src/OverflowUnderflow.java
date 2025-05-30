
public class OverflowUnderflow {
	public static void main(String[] args) {
		System.out.println("Overflow");
		int x=1;
		while(x>0) {
			x*=1000;
			System.out.println(x);
		}
		
		System.out.println("Underflow");
		while(x<0) {
			x*=1000;
			System.out.println(x);
		}
	}
}
