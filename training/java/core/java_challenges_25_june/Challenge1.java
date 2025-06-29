package training.java.core.java_challenges_25_june;

public class Challenge1 {
	public static void main(String args[]) {
		byte byteData = 123;
		short shortData = 3245;
		int intData = 1293805;
		
		long result = 50000L + 10 * (byteData + shortData + intData);
		
		System.out.println(result);
	}
}
