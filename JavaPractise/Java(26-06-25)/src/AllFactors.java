
public class AllFactors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printFactors(32);
	}
	public static void printFactors(int num)
	{
		if(num<1)
		{
			System.out.println("Invalid value");
		}
		int i=1;
		while(i<=num)
		{
			if(num%i==0)
			{
				System.out.print(i+" ");
			}
			i++;
		}
		
	}

}
