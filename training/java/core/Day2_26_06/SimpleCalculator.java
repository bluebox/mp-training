package Day2_26_06;

public class SimpleCalculator {
	private double firstNumber;
	private double secondNumber;
	public SimpleCalculator(double firstNumber, double secondNumber) {
		super();
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
	}
	public double getFirstNumber() {
		return firstNumber;
	}
	public void setFirstNumber(double firstNumber) {
		this.firstNumber = firstNumber;
	}
	public double getSecondNumber() {
		return secondNumber;
	}
	public void setSecondNumber(double secondNumber) {
		this.secondNumber = secondNumber;
	}
	public double getAddition() {
		return firstNumber+secondNumber;
	}
	public double getDivision() {
		return firstNumber/secondNumber;
	}
	public double getSubtraction() {
		return firstNumber-secondNumber;
	}
	public double getMultiplication() {
		return firstNumber*secondNumber;
	}
	   public static void main(String[] args) {
	        SimpleCalculator calculator = new SimpleCalculator(10.0, 5.0);

	        System.out.println("First Number: " + calculator.getFirstNumber());
	        System.out.println("Second Number: " + calculator.getSecondNumber());

	        System.out.println("Addition: " + calculator.getAddition());
	        System.out.println("Subtraction: " + calculator.getSubtraction());
	        System.out.println("Multiplication: " + calculator.getMultiplication());

	        if (calculator.getSecondNumber() != 0) {
	            System.out.println("Division: " + calculator.getDivision());
	        } else {
	            System.out.println("Division: Cannot divide by zero.");
	        }

	        calculator.setFirstNumber(20.0);
	        calculator.setSecondNumber(4.0);
	        System.out.println("\nAfter updating values:");
	        System.out.println("Addition: " + calculator.getAddition());
	        System.out.println("Subtraction: " + calculator.getSubtraction());
	        System.out.println("Multiplication: " + calculator.getMultiplication());
	        System.out.println("Division: " + calculator.getDivision());
	    }
}
