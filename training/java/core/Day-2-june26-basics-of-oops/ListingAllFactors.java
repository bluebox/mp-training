package day_2_june26_basics_of_oops;

public class ListingAllFactors {

	public static void main(String[] args) {
		printFactors(6);
		printFactors(32);
		printFactors(10);
		printFactors(-1);
	}

	public static void printFactors(int number) {
		if (number<=0) {
			System.out.println("invalid value");
		}
		for(int i=1;i<=number;i++) {
			if (number%i==0) {
				System.out.print(i+" ");
			}
		}
		System.out.println();
	}
}
