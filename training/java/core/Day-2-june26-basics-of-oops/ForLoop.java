package day2;

public class ForLoop {

	public static void main(String[] args) {
		int start=2,end=300;
		int counter=0;
		for (int i=start;i<=end;i++) {
			if (isPrime(i)) {
				counter++;
				System.out.print(i+" ");
			}
		}
		System.out.println("\ncounter is "+counter);
	}
	public static boolean isPrime(int number) {
		for(int i=2;i<number;i++) {
			if (number%i==0) {
				return false;
			}
		}
		return true;
	}
}
