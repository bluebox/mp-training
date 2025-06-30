package corejava.june26_methods;

public class OddNumberSumInRange {

	public static void main(String[] args) {
		
	}
	
	//Checking whether a number is odd or not
	public static boolean isOdd(int number) {
		return (number > 0) ?(number & 1) != 0 : false;
	}
	
	//To sum odd numbers in a given range
	public static int sumOdd(int start,int end) {
		int sum = 0;
		for(int i = start; i <= end; i++) {
			if(isOdd(i))
				sum += i;	
		}
		return sum;
	}
}
