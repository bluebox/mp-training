package Practice.june25;

/*Write a Java program that:

Declares two variables: an integer and a float.
Assigns them values using literals.
Calculates the sum, difference, product, and quotient of these variables (cast appropriately if needed).
Prints the results.*/

public class DataTypesAndOperators {

	public static void main(String[] args) {
		int intVariable = 123;
		float floatVariable = 20.0f;
		float sum = intVariable + floatVariable;
		float diff = intVariable - floatVariable;
		float prd = intVariable * floatVariable;
		float quotient = intVariable / floatVariable ;
		System.out.println("Sum of the given two variables "+intVariable+", "+floatVariable+" is: "+sum);
		System.out.println("difference between the given two variables "+intVariable+", "+floatVariable+" is: "+diff);
		System.out.println("product of the given two variables "+intVariable+", "+floatVariable+" is: "+prd);
		System.out.println("Quotient of the given two variables "+intVariable+", "+floatVariable+" is: "+quotient);
	}

}
