package operators;

public class BinaryReversal {
	
	int originalNumber = 125436;
	String origBinaryEquv = Integer.toBinaryString(originalNumber);
	int length = origBinaryEquv.length();
	int reverseNumber = originalNumber>>length;
	System.out.println();
}
