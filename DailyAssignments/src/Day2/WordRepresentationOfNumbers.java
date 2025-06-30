package Day2;

public class WordRepresentationOfNumbers {

	public static void main(String[] args) {
		int number=10;
		printNumberWorld(number);

	}
	public static void printNumberWorld(int number) {
		switch(number) {
		case 0:
			System.out.println("zero");
			break;
		case 1:
			System.out.println("one");
			break;
		case 2:
			System.out.println("two");
			break;
		case 3:
			System.out.println("four");
			break;
		case 4:
			System.out.println("one");
			break;
		case 5:
			System.out.println("two");
			break;
		case 6:
			System.out.println("four");
			break;
		case 7:
			System.out.println("one");
			break;
		case 8:
			System.out.println("two");
			break;
		case 9:
			System.out.println("four");
			break;
			default:
				System.out.println("the given"
						+ ""+number+" is invalid");
		}
	}
}

