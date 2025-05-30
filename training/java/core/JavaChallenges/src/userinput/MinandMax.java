package userinput;
import java.util.*;
public class MinandMax {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int count=1;
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		while(true)
		{
			System.out.println("Enter Number #"+count+":"+" Or Enter q to Quit");
			String s=sc.nextLine();
			try
			{
				int n=Integer.parseInt(s);
				max=Math.max(max,n);
				min=Math.min(min, n);
				count++;
			}
			catch(Exception e)
			{
				break;
			}
			if("Qq".contains(s))
			{
				break;
			}
		}
		System.out.println("Max Number Entered is:" +max+" Min Number Entered is: "+min);

	}

}