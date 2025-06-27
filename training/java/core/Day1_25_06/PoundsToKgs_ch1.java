package Day1_25_06;

public class PoundsToKgs_ch1 {
	
//	The objective of this challenge, is to convert a given number of pounds to kilograms.
//	STEPS:
//	1. Create a variable with the appropriate type, to store the number of pounds that we want to convert into kilograms.
//	2. Calculate kilograms, using the variable above, and store the result in a 2nd appropriately typed variable.
//	3. Print the result.
//	Don't forget to use the conversion formula shown here:
//	1 pound is equal to 0.45359237 of a kilogram.
	
	
	public static void main(String args[]) {
		
		double pounds=15;
		double kgs=pounds*0.45359237;
		System.out.println("After Convertion to kgs ="+ kgs);
	}
}
