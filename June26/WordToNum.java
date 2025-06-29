package June26;

public class WordToNum {
	public static void main(String[] args) {
		System.out.println(printNumberInWord("SEVEN"));
		System.out.println(printNumberInWord("SEVENTEEN"));
	}
	public static int printNumberInWord(String word) {
		
		/*int number = -1;
		
		if (word == "ZERO") number = 0;
		else if (word == "ONE") number = 1;
		else if (word == "TWO") number = 2;
		else if (word == "THREE") number = 3;
		else if (word == "FOUR") number = 4;
		else if (word == "FIVE") number = 5;
		else if (word == "SIX") number = 6;
		else if (word == "SEVEN") number = 7;
		else if (word == "EIGHT") number = 8;
		else if (word == "NINE") number = 9;
		
		return number;*/
		
		return switch(word) {
		case "ZERO" -> 0;
		case "ONE" -> 1;
		case "TWO" -> 2;
		case "THREE" -> 3;
		case "FOUR" -> 4;
		case "FIVE" -> 5;
		case "SIX" -> 6;
		case "SEVEN" -> 7;
		case "EIGHT" -> 8;
		case "NINE" -> 9;
		default -> -1;
		};
	}
}
