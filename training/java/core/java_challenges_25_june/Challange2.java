package training.java.core.java_challenges_25_june;

public class Challange2 {
	public static void main(String args[]) {
		
		//if pounds input is an integer
		int pounds = 100;
		double kgConverted = pounds * 0.45359237d;
		System.out.println(pounds+" Pounds = "+ kgConverted + " KiloGrams");
		
		
		//if pounds input is a decimal
		double poundsFrac = 200.30d;
		double kgConverted1 = poundsFrac * 0.45359237d;
		System.out.println(poundsFrac+" Pounds = "+ kgConverted1 + " KiloGrams");

	}
}
