package training.java.core.java_challenges_25_june;


public class Challenge3 {
	public static void main(String args[]) {
		
		double num1 = 20.00d;
		double num2 = 80.00d;
		double sum = (num1+num2) * 100.00;
		
		double remainder = sum % 40.00d;
		
		boolean isDivisible = (remainder == 0.00d) ? true : false;
		System.out.println("value of the boolen: " + isDivisible);
		
		if(!isDivisible) {
			System.out.println("got some remainder");
		}
		
	}
}
