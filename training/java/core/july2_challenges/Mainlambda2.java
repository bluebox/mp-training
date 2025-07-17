package june2_challenges;
import java.util.Scanner;
import java.util.function.*;
public class Mainlambda2 {
	public static String everySecondCharacter(UnaryOperator<String> str,String s) {
		return str.apply(s);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		//System.out.println("Enter the String");
		//String s=sc.nextLine();
		UnaryOperator<String> everySecondChar=(source)->{
			StringBuilder returnVal=new StringBuilder();
			for(int i=0;i<source.length();i++) {
				if(i%2==1) {
					returnVal.append(source.charAt(i));
				}
				
			}
			return returnVal.toString();

		};
		System.out.println("Result of lambda expression");
		System.out.println(everySecondChar.apply("1234567890"));
		System.out.println("Result of everySecondCharacter method");
		System.out.println(everySecondCharacter(everySecondChar,"1234567890"));
		
		System.out.println("Supplier interface implemtation");
		Supplier<String> iLoveJava=()->"I Love Java";
		String supplierResult=iLoveJava.get();
		System.out.println(supplierResult);
		
	}

}
