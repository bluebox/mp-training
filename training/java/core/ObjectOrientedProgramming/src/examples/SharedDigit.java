package examples;

public class SharedDigit {
	
	public static boolean hasSharedDigit(int number1, int number2)
	{
		if(number1<9||number1>100||number2>100||number2<9)
		{
			return false;
		}
		boolean result = false;
		String str1 = Integer.toString(number1);
		String str2 = Integer.toString(number2);
		for(int i = 0;i<str1.length();i++)
		{
			if(str2.contains(Character.toString(str1.charAt(i))))
			{
				result = true;
				break;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(hasSharedDigit(99,59));
		System.out.println(hasSharedDigit(22,23));
		System.out.println(hasSharedDigit(67,54));
		
		
	}
}
