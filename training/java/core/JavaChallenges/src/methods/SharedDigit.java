package methods;
import java.util.*;
public class SharedDigit {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		int a=sc.nextInt();
		int b=sc.nextInt();
		boolean res=hasSharedDigit(a,b);
		System.out.println(res);
	}
	public static boolean hasSharedDigit(int a,int b)
	{
		HashSet<Integer> hs=new HashSet<>();
		while(a>0)
		{
			hs.add(a%10);
			a=a/10;
		}
		while(b>0)
		{
			if(hs.contains(b%10))
			{
				return true;
			}
			b=b/10;
		}
		return false;
	}

}
