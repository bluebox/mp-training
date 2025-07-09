package Day9_08_07_miniChallanges;

public class MainMini {
	public static void main(String[] args) {
		//challenge1
		String challenge1="Hello World!";
		System.out.println(challenge1.matches("Hello World!"));//true
		//challenge2
		String challengep="^[A-Z]*[.?!]?";
		System.out.println("Hello?".matches(challengep));//false
		System.out.println("H?".matches(challengep));//true
		
		String challenge2="^[A-Z].*[.]?";
		System.out.println("Hello?".matches(challenge2));//false
		System.out.println("H?".matches(challenge2));//true
		System.out.println("hello?".matches(challenge2));//false
		System.out.println("Hello".matches(challenge2));//false

		//challenge3
		String challenge3="^[A-Z].*[.?!]?";
		System.out.println("The bike is red,and has flat tires.".matches(challenge3));//true
		System.out.println("I love being a L.P.A student!".matches(challenge3));//true
		System.out.println("Hello Friends and family :Welcome!".matches(challenge3));//true
		System.out.println("How are You Mary?".matches(challenge3));//true
		
	}
}
