package corejava.june26_Classes;

public class SimpleCalculator {
	private double firstNumber;
	private double secondNumber;
	public SimpleCalculator(double firstNumber, double secondNumber) {
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
	public double getAdditionResult() {
		return this.firstNumber+this.secondNumber;
	}
	public double getSubtractionResult() {
		return this.secondNumber-this.firstNumber;
	}
	public double getMultiplicationResult() {
		return this.firstNumber*this.secondNumber;
	}
	public double getDivisionResult() {
		if(this.secondNumber==0)
			return 0;
		return this.firstNumber/this.secondNumber;
	}
}
