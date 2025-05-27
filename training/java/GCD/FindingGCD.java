package GCD;

public class FindingGCD {

	public static void main(String[] args) {
		int valueOne=9,valueTwo=16;
		int gcd = getGreatestCommonDivisor(valueOne,valueTwo);
		System.out.println( "the gcd is :"+ gcd);
	}
	public static int getGreatestCommonDivisor(int valueOne, int valueTwo)
	{
		if(valueOne < 10 || valueTwo < 10)
		{
			return -1;
		}
		int gcd=1;
		int limit= Math.min(valueTwo, valueOne);
		for(int i=2;i<=limit;i++)
		{
			if( (valueOne % i == 0 ) && (valueTwo % i == 0))
			{
				gcd=i;
			}
		}
		return gcd;
	}

}
