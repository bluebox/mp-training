package corejava.june26_Classes;

import java.util.Scanner;

public class CalculatorMain {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter FirstNumber");
		double firstNumber=sc.nextDouble();
		System.out.println("Enter secondNumber");
		double secondNumber=sc.nextDouble();
		SimpleCalculator cal=new SimpleCalculator(firstNumber,secondNumber);
		System.out.println("You entered first number as "+cal.getFirstNumber()+" and second number as "+cal.getSecondNumber());
		System.out.println("sum of the two numbers is: "+cal.getAdditionResult());
		System.out.println("Difference between two numbers is:"+cal.getSubtractionResult());
		System.out.println("product of two numbers is:"+cal.getMultiplicationResult());
		System.out.println("Division of two numbers is:"+cal.getDivisionResult());
		sc.close();
	}

}
