package examples;

public class DigitWorded {
	
	public static int getDigitcount(int number)
	{
		int count = 0;
		number = Math.abs(number);
		while(number >0)
		{
			count+=1;
			number/=10;
		}
		return count;
	}
	
	
	public static int reverse(int number)
	{
		int numberReverse = 0;
		boolean flag = number<0?true:false;
		number = Math.abs(number);
		while(number >0)
		{
			int digit = number%10;
			numberReverse = numberReverse*10+digit;
			number = number/10;
		}
		return flag?-numberReverse:numberReverse;
	}
	
	
	public static String numberToWords(int number)
	{
		if(number<0)
		{
			return "Invalid";
		}
		String result = "";
		while(number >0)
		{
			int digit = number%10;
			switch(digit) {
			case 0->result = "Zero "+result;
			case 1->result = "One "+result;
			case 2->result = "Two "+result;
			case 3->result = "Three "+result;
			case 4->result = "Four "+result;
			case 5->result = "Five "+result;
			case 6->result = "Six "+result;
			case 7->result = "Seven "+result;
			case 8->result = "Eight "+result;
			case 9->result = "Nine "+result;
			}
			number = number/10;
		}
		return result;
	}

	
	public static void main(String[] args) {
		System.out.println(numberToWords(10030004));
		System.out.println(numberToWords(654));
		System.out.println(numberToWords(1234567890));
		
		System.out.println(reverse(212));
		System.out.println(reverse(-873));
		System.out.println(reverse(345623));
		
		System.out.println(getDigitcount(1003));
		System.out.println(getDigitcount(53478));
		System.out.println(getDigitcount(-1048923));
		
		
	}
}
