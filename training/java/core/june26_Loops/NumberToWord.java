package june26_Loops;
import java.util.Scanner;
public class NumberToWord {
	public static String word(int num) {
		return switch(num) {
			case 1->"One";
			case 2 -> "Two";
			case 3 -> "Three";
			case 4 -> "Four";
			case 5 -> "Five";
			case 6 -> "Six";
			case 7 -> "Seven";
			case 8 -> "Eight";
			case 9 -> "Nine";
			default -> "Others";
		};
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number that need to convert to word");
		int num=sc.nextInt();
		System.out.println(num+" to word :"+word(num));
		sc.close();
		
	}

}
