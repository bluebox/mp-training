package day_2_june26_basics_of_oops;

public class NumberInWord {

	public static void main(String[] args) {
		printNumberInWord(4);
		printNumberInWord(34);
	}
	
	public static void printNumberInWord(int number) {
		switch(number) {
			case 0:
				System.out.println("ZERO");
				break;
			case 1:
				System.out.println("ONE");
				break;
			case 2:
				System.out.println("TWO");
				break;
			case 3:
				System.out.println("THREE");
				break;
			case 4:
				System.out.println("FOUR");
				break;
			case 5:
				System.out.println("FIVE");
				break;
			case 6:
				System.out.println("SIX");
				break;
			case 7:
				System.out.println("SEVEN");
				break;
			case 8:
				System.out.println("EIGHT");
				break;
			case 9:
				System.out.println("NINE");
				break;
			default:
				System.out.println("other");
		}
	}
}
