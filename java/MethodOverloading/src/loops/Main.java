package loops;

public class Main {
	public static void main(String[] args) {
		int myNumber = 1;
		int counter =0;
		int sum = 0;
		for(myNumber=1;myNumber<1000&&counter<5;myNumber++) {
			if(myNumber%3==0 && myNumber%5==0) {
				sum+=myNumber;
				counter++;
			}
		}
		System.out.println("Sum of First 5 numbers is " + sum);
	}

}
