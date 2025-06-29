
public class Challenge20 {
	public static int gcd(int a,int b)
	{
		int ans=0;
		for(int i=1;i<Math.max(a, b);i++)
		{
			if(a%i==0 && b%i==0)
			{
				ans=Math.max(ans, i);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(12,30));

	}

}
