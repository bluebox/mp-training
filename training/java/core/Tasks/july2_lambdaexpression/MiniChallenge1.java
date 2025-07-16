package corejava.july2_lambdaexpression;

import java.util.Scanner;
import java.util.function.Consumer;

public class MiniChallenge1 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a list of strings by seperating them with ','");
		String string=sc.nextLine();
		Consumer<String> printTheParts= str->{
			String[] parts=string.split(",");
			for(String part:parts) {
				System.out.println(part);
			}
		};
		printTheParts.accept(string);
		sc.close();
	}

}
