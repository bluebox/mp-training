package examples;

public class GreatestCommonDivisor {
	public static int getGreatestcommonDivisor(int...operands)
	{
		int mini = Integer.MAX_VALUE;
		for(int operand:operands)
		{
			if(operand<10)
			{
				return -1;
			}
			mini = Math.min(operand, mini);
		}
		int gcd = -1;
		for(int i = 1;i<=mini;i++)
		{
			boolean flag = false;
			for(int operand:operands)
			innerbloc:{
				if(operand%i != 0)
				{
					flag = true;
					break innerbloc;
				}
			}
			if(!flag)
			{
				gcd = i;
			}
		}
		return gcd;
	}
	
	public static void main(String[] args) {
		System.out.println(getGreatestcommonDivisor(64,32,24));
		System.out.println(getGreatestcommonDivisor(8,32,24));
	}
}
