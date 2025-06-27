
public class FindNoOfBuckets {

	public static void printEqual(int a,int b,int c)
	{
		if(a<0 || b<0 || c<0)
			System.out.println("Invalid value");
		else if(a==b && a==c)
			System.out.println("All numbers are equal");
		else if(a!=b && a!=c && b!=c)
		{
			System.out.println("All numbers are different");
		}
		else
			System.out.println("Neither all are equal or different");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printEqual(1,1,1);
		printEqual(-1,-1,1);
		printEqual(1,2,3);
		printEqual(1,1,2);
		

	}

}
