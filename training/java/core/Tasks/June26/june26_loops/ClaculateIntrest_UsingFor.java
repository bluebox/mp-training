package corejava.june26_loops;

public class ClaculateIntrest_UsingFor {

	public static void main(String[] args) {
		int amount=100;
		for(float rate=7.5f;rate<=10;rate+=0.25) {
			System.out.println("The intrest for $"+amount+" for intrest rate "+rate+"% is: $"+calculateIntrest(amount,rate));
		}
	}
	
	public static float calculateIntrest(int amount,float rate) {
		float intrest = amount * (rate/100);
		return intrest;
	}
}
