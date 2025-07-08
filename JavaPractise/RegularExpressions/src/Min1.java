import java.util.Scanner;
import java.util.*;

public class Min1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter the string for matching:");
		String input =sc.nextLine();
		if(input.matches("Hello World.")) {
			System.out.println("Matched");
		}
		else {
			System.out.println("Not Matched");
		}
	}
}
