package Methods;

public class EvenNumber {
	public static void main(String args[]) {
		int number = 5;
		while (number <= 20) {
			if (isEvenNumber(number)) {
				System.out.print(number+" ");
			}
			number++;
		}
	}

	public static boolean isEvenNumber(int number) {
		return (number%2 == 0);
	}
}
