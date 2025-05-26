package learn;

public class PrintFactors {
	public static void main(String[] args) {
		
		int number = -40;
		printAllFactors(number);
	}
	
	public static void printAllFactors(int number) {
		
		if(number < 1) {
			System.out.println("Invalid Value");
			return;
		}
			
		for(int i = 1; i <=number ; i++) {
			
			if(number % i == 0) {
				System.out.println(i);
			}
		}
	}
}
