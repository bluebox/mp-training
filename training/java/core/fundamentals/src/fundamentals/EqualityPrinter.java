package fundamentals;

public class EqualityPrinter {
	public static void main(String args[])
	{
		printEqual(1,1,1);
		printEqual(1,1,2);
		printEqual(-1,-1,-1);
		printEqual(1,2,3);
	}
	static void printEqual(int a,int b,int c)
	{
		if(a<0 || b<0 || c<0)
		{
			System.out.println("Invalid value.");
			return;
		}
		else if(a==b && b==c)
		{
			System.out.println("All numbers are equal.");
		}
		else if(a==b || b==c || a==c)
		{
			System.out.println("Neither equal nor different.");
		}
		else {
			System.out.println("All are different");
		}
	}
}
