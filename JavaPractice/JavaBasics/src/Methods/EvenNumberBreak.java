package Methods;

public class EvenNumberBreak {
	public static void main(String args[]) {
		int number = 5,countEven = 0,countOdd = 0;
		while (number <= 20) {
			if (isEvenNumber(number)) {
				System.out.print(number+" ");
				countEven++;
				if (countEven == 5) {
					break;
				}
			}
			else {
				countOdd++;
			}
			number++;
		}
		System.out.println("\nEven numbers count = "+countEven);
		System.out.println("Odd numbers count = "+countOdd);
	}

	public static boolean isEvenNumber(int number) {
		return ((number&1) == 0);
	}
}
