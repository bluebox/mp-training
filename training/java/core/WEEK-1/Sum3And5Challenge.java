public class Sum3And5Challenge {
	public static void main(String[] args) {
		int counter = 0;
		int sum = 0;
		for(int i=1 ; i<= 1000 && counter < 3; i++) {
			if(i % 3 == 0 && i % 5 == 0) {
				counter++;
				sum+=i;
				System.out.println(i+" is divisible by 3 and 5");
			}
		}
		System.out.println("Sum of such 3 numbers = " + sum);
	}
	
}