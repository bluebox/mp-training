package examples;

public class Factors {
	
	public static String primeFactors(int number)
	{
		String result = "";
		if (number<1)
		{
			return "Invalid";
		}else
		{
			for(int i = 1;i<=number/2;i++)
			{
				result+=""+((number%i == 0)?" "+Integer.toString(i):"");
			}
		}
		return result+" "+Integer.toString(number);
	}
	public static void main(String[] args) {
		System.out.println(primeFactors(10));
		System.out.println(primeFactors(100));
		System.out.println(primeFactors(11));
		System.out.println(primeFactors(64));
	}
}
