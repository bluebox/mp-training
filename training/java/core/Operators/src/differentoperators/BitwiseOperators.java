package differentoperators;

public class BitwiseOperators {
	public static void main(String args[]) {
		int a = 5;
		int b = 10;
		int and = a & b;
		int or = a | b;
		int xor = a ^ b;
		int rightShift = a >> 1;
		int leftShift = b << 1;
		System.out.println(and);
		System.out.println(or);
		System.out.println(xor);
		System.out.println(rightShift);
		System.out.println(leftShift);
	}

}
