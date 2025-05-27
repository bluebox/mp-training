package examples;

public class PalindromicCheck {
	public static boolean isPalindrome(int number)
	{
		number = Math.abs(number);
		String numericString = Integer.toString(number);
		for(int i = 0;i<numericString.length()/2;i++)
		{
			if(numericString.charAt(i) != numericString.charAt(numericString.length()-1-i))
			{
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println(isPalindrome(-10001));
		System.out.println(isPalindrome(1));
	}
}
