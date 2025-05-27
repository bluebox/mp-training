package examples;

public class PerfectNumber {
	
	public static boolean isPerfectNumber(int number)
	{
		int primeSum = 0;
		for(int i = 1;i<=number/2;i++)
		{
			primeSum += (number%i == 0)?i:0;
		}
		return primeSum == number;
	}
	
	public static void main(String[] args) {
		System.out.println(isPerfectNumber(6));
		System.out.println(isPerfectNumber(28));
		System.out.println(isPerfectNumber(5));
		
	}

}
