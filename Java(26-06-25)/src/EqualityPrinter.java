
public class EqualityPrinter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		equalityPrinterMultipleConditions(1,2,3);

	}
	public static void equalityPrinterMultipleConditions(int a,int b,int c)
	{
		if(a<0||b<0||c<0)
		{
			System.out.println("Invalid value");
		}
		else if(a==b&&b==c&&c==a)
		{
			System.out.println("All numbers are equal");
		}
		else if(a!=b&&b!=c&&c!=a)
		{
			System.out.println("All numbers are differernt");
		}
		else
		{
			System.out.println("Neither all are equal or different");
		}
	}

	
}
