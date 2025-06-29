
public class Challenge21 {

	public static void printFactors(int n)
	{
		for(int i=1;i<=n;i++)
		{
			if(n%i==0)
				System.out.println(i+" ");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printFactors(30);

	}

}
