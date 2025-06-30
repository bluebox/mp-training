package Day2;

public class SwitchChallenge1 {

	public static void main(String[] args) {
		char value='D';
		switch(value) {
		case 'A':
			System.out.println("A is able");
			break;
		case 'B':
			System.out.println("b is ball");
			break;
		case 'C':
			System.out.println("C is courage");
			break;
		case 'D':
			System.out.println("D is dream");
			break;
		case 'E':
			System.out.println("E is eager");
			break;
		case 'F':
			System.out.println("F is force");
			break;
			default:
				System.out.println("letter "+ value + " is not found in switch value");
		}
	}

}
