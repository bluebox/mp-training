package corejava.july2_lambdaexpression;

import java.util.Scanner;
import java.util.function.UnaryOperator;

public class MiniChallenge2 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a string");
		String string=sc.nextLine();
		UnaryOperator<String> everySecondChar= str-> {
				StringBuilder returnVal=new StringBuilder();
				for(int i=0;i<str.length();i++) {
					if(i%2==1) {
						returnVal.append(str.charAt(i));
					}
				}
				return returnVal.toString();
		};
		System.out.println(everySecondChar.apply(string));
		sc.close();
	}

}
