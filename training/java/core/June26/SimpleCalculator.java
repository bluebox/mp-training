package June26;

public class SimpleCalculator {
	
	private double firstNumber;
	private double secondNumber;
	
	public SimpleCalculator() {
		firstNumber = 0;
		secondNumber = 0;
	}
	
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
	
	public double getAdditionResults() {
		return this.firstNumber + this.secondNumber;
	}
	
	public double getSubtractionResults() {
		return this.firstNumber - this.secondNumber;
	}
	
	public double getMultiplicationResults() {
		return this.firstNumber * this.secondNumber;
	}
	
	public double getDivisionResults() {
		if (this.secondNumber == 0) return 0;
		return this.firstNumber / this.secondNumber;
	}
	
}
