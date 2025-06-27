package day_2_june26_basics_of_oops;

public class EvenNumbers2 {
	public static void main(String[] args) {
		int evenCounter = 0;
		int oddCounter = 0;

		for (int i = 5; i <= 20 && evenCounter < 5; i++) {
			if (isEvenNumber(i)) {
				System.out.println(i);
				evenCounter++;
			} else {
				oddCounter++;
			}
		}

		System.out.println("Number of evens are " + evenCounter);
		System.out.println("Number of odds are " + oddCounter);
	}

	public static boolean isEvenNumber(int number) {
		return number % 2 == 0;
	}
}
