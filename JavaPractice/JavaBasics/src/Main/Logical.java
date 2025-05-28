package Main;

public class Logical {
	public static void main(String args[]) {
		int a = 5, b = 4, c = 6;
		
		System.out.println((a > b) && (a > c));
		System.out.println((c < b) && (a < c));
		System.out.println((a > b) || (a > c));
		System.out.println((b > a) || (c > b));
		System.out.println(!(a < b));
	}
}
