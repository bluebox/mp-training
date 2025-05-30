package challenges;

import java.util.*;

public class DailyChallenge3 {
	public static void main(String []args)
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int []persons=new int[n];
		int []dishes=new int[n];
		for(int i=0;i<n;i++)
		{
			persons[i]=sc.nextInt();
		}
		for(int i=0;i<n;i++)
		{
			dishes[i]=sc.nextInt();
		}
		int res=remainingPeople(persons,dishes);
		System.out.println(res);
	}
	public static int remainingPeople(int []p,int d[])
	{
		LinkedList<Integer> q1=new LinkedList<>();
		LinkedList<Integer> q2=new LinkedList<>();
		for(int i:p)
		{
			q1.offerLast(i);
		}
		for(int i:d)
		{
			q2.offerLast(i);
		}
		int count=0;
		while(count<q1.size())
		{
			int v=q1.pollFirst();
			if(v==q2.peekFirst())
			{
				q2.pollFirst();
				count=0;
			}
			else
			{
				q1.offerLast(v);
				count++;
			}
		}
		return q1.size();
	}

}
