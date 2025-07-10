package June26;

public class SimpleCalculatorMain {
	public static void main(String[] args) {
		SimpleCalculator calculator = new SimpleCalculator();

		calculator.setFirstNumber(5.0);
		calculator.setSecondNumber(4);

		System.out.println("add = " + calculator.getAdditionResults());
		System.out.println("subtract = " + calculator.getSubtractionResults());

		calculator.setFirstNumber(5.25);
		calculator.setSecondNumber (0);

		System.out.println("multiply = " + calculator.getMultiplicationResults());
		System.out.println("divide = " + calculator.getDivisionResults());
	}
}
