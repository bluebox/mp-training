
public class SumCalculatorImplementaion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator c1=new Calculator();
		c1.setfirstNumber(5.0);
		c1.setSecondNumber(4);
		System.out.println("add = "+c1.getAddition());
		System.out.println("sub = "+c1.getSubtraction());
		c1.setfirstNumber(5.25);
		c1.setSecondNumber(0);
		System.out.println("mul= "+c1.getMultiplication());
		System.out.println("div = "+c1.getDivision());
	}
}
  class Calculator
	{
		private double firstNumber;
		private double secondNumber;
		public void setfirstNumber(double firstNumber) {
			this.firstNumber = firstNumber;
		}
		public double getfirstNumber() {
			return firstNumber;
		}

		public void setSecondNumber(double secondNumber) {
			this.secondNumber = secondNumber;
		}
		public double getSecondNumber() {
			return secondNumber;
		}
		public double getAddition()
		{
			return firstNumber+secondNumber;
		}
		public double getSubtraction()
		{
			return firstNumber-secondNumber;
		}
		public double getMultiplication()
		{
			return firstNumber*secondNumber;
		}
		public double getDivision()
		{
			if(secondNumber==0)
				return 0;
			else
			return firstNumber/secondNumber;
		}
	}
  

