package training_java.May26;

public class Factors {
	public static void printFactors(int number) {
		if(number<1) {
			System.out.println("Invalid value");
		}
		int factor=1;
		while(factor<=number) {
			if(number%factor==0) {
				System.out.print(factor+ " ");
			}
			factor++;
		}
		System.out.println();
	}
public static void main(String[] args) {
	printFactors(32);
	printFactors(56);
}
}
