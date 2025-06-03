package day8.calculator;

public class SimpleCalculator {
 private double firstNumber,secondNumber;
 
 public double getFirstNumber() {
	 return this.firstNumber;
 }
 public double getSecondNumber() {
	 return this.secondNumber;
 }
 public void setFirstNumber(double inp) {
	 this.firstNumber=inp;
 }
 public void setSecondNumber(double inp) {
	 this.secondNumber=inp;
 }
 public double getAdditionResult() {
	 return firstNumber+secondNumber;
 }
 public double getSubtractionResult() {
	 return firstNumber-secondNumber;
 }
 public double getMultiplicationResult() {
	 return firstNumber*secondNumber;
 }
 public double getDivisionResult() {
	 if(getSecondNumber()==0)
		 return 0;
	 return firstNumber/secondNumber;
 }
}
