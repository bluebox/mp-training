package Day2_26_06;

public class WordNum {
	public static void main(String args[]) {
		for(int i=-2;i<12;i++) {
		System.out.println(printNumberinWord(i));
		}
	}
	public static String printNumberinWord(int num) {
		return switch(num) {
		case 1 ->"ONE";
		case 2 -> "TWO";
		case 3 -> "THREE";
		case 4 ->"FOUR";
		case 5 ->"FIVE";
		case 6 -> "SIX";
		case 7 -> "SEVEN";
		case 8 -> "EIGHT";
		case 9 ->"NINE";
		default -> "OTHER";
		};
	}
}
