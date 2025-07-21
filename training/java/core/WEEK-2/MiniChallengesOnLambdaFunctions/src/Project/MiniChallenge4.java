package Project;

import java.util.function.Predicate;
import java.util.Scanner;

public class MiniChallenge4 {
	
	public void everySecondCharacter(Predicate<Integer> pred,String str) {
		StringBuilder outputString = new StringBuilder(); 
		for(int i=0; i<str.length(); i++) {
			if(pred.test(i)) {
				outputString.append(str.charAt(i));
			}
		}
		System.out.println(outputString.toString());
	}
	
	public void getEvenCharString() {
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the String : ");
		String source=sc.nextLine();
		Predicate<Integer> pred=i -> i%2==1;
		sc.close();
		everySecondCharacter(pred, source);
	} 
	
	public void getEvenCharString(String str) {
		System.out.print("Enter the String : ");
		Predicate<Integer> pred=i -> i%2==1;
		everySecondCharacter(pred, str);
	}
}
