package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface A
{
	public void countWord();
}
public class Ex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a1=()->{
			int sum1=0,sum2=0;
			List<Integer> a=Arrays.asList(1,2,3,4,5,6,7,8);
			for(int i=0;i<a.size();i++)
			{
				if(a.get(i)%2==0)
				{
					sum1+=(a.get(i)*a.get(i));
				}
				else
				{
					sum2+=(a.get(i)*a.get(i));
				}
			}
	
			System.out.println(sum1+" "+sum2);
			
			
		};
		a1.countWord();
		
		
		

	}

}
