
public class Calculator {
	private double firstNumber;
	private double secondNumber;
	
	public double getFirstNumber() {
		return this.firstNumber;
	}
	public void setFirstNumber(double firstNumber) {
		this.firstNumber = firstNumber;
	}
	public double getSecondNumber() {
		return this.secondNumber;
	}
	public void setSecondNumber(double secondNumber) {
		this.secondNumber = secondNumber;
	}
	
	public double additionResult() {
		return this.firstNumber+this.secondNumber;
	}
	public double subractionResult() {
		return this.firstNumber - this.secondNumber;
		
	}
	public double multiplicationResult() {
		return this.firstNumber * this.secondNumber;
		
	}
	public double divisionResult() {
		if(this.secondNumber== 0) {
			return 0;
		}
		return this.firstNumber / this.secondNumber;
		
	}
	
}
