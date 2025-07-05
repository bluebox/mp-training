
public class DigitsintoWords {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getDigitCount(121));
		getReverse(-121);
		numberToWord(251);
	}
	public static int getDigitCount(int num)
	{
		if(num<0)
		{
			return -1;
		}
		int count=0;
		while(num!=0)
		{
			count++;
			num=num/10;
		}
		return count;
	}
	public static void getReverse(int num)
	{
		int num1=num;
		if(num<0)
		{
			num=Math.abs(num);
		}
		int rev=0;
		while(num!=0)
		{
			int rem=num%10;
			rev=rev*10+rem;
			num=num/10;
		}
		if(num1<0)
		{
			System.out.println("-"+rev);
		}
		else
			System.out.println(rev);
	}
	public static void numberToWord(int num)
	{
		int count=0;
		if(num<0)
		{
			System.out.println("Invalid");
		}
		int rev=0;
		while(num!=0)
		{
			int rem=num%10;
			rev=rev*10+rem;
			num=num/10;
			count++;
		}
		String s="";
		for(int i=0;i<count;i++)
		{
			int rem=rev%10;
			switch(rem)
			{
			case 0->s+="Zero";
			case 1->s+="One";
			case 2->s+="Two";
			case 3->s+="Three";
			case 4->s+="Four";
			case 5->s+="Five";
			case 6->s+="Six";
			case 7->s+="Seven";
			case 8->s+="Eight";
			case 9->s+="Nine";
			}
			rev=rev/10;
		}
		System.out.println(s);
		
	}
	

}
