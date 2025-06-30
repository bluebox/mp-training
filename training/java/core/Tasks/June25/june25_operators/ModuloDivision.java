package corejava.june25_operators;

public class ModuloDivision {

	public static void main(String[] args) {
		double firstVariable=20.00;
		double secondVariable=80.00;
		double remainder=(firstVariable+secondVariable)*100.00 % 40.00; //operator precedence is considered here
		boolean isZero;
		if(remainder==0.00) {
			isZero=true;
		}
		else {
			isZero=false;
		}
		System.out.println(remainder);
		if(!isZero) {
			System.out.println("Got some remainder");
		}
	}

}
