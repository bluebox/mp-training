package Day1_25_06;

public class PremitiveTypes_ch3 {

	public static void main(String[] args) {
//		Your challenge is to create four new variables:
//			A byte variable, set it to any valid byte number, it doesn't matter.
//			A short variable, set it to any valid short number.
//			An int variable, set it to any valid integer number.
//			A Lastly, create a variable of type long. Make it equal to 50,000 plus 10 times the sum of the values of the first 3 variables (your byte, your short and your int values). In other words, use the variable names in your expression to calculate the sum.
	
		byte myByte = 25;
		short myShort =150;
		int myInt = 652;
		long result=50000L+10*(myByte+myShort+myInt);
		
		System.out.println("The result is "+ result);
	}
}
