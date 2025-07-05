import java.util.Scanner;
public class MinMaxChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		minMax();

	}
	public static void minMax()
	{
		Scanner sc=new Scanner(System.in);
		String num;
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		while(true)
		{
			try
		{
			System.out.println("enter the number until loop exits");
			num=sc.nextLine();
			int num1=Integer.parseInt(num);
			max=Math.max(max, num1);
			min=Math.min(min, num1);
			
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("we are exiting the loop because its not a number");
			break;
		}
		}
		System.out.println("maximum value is "+max);
		System.out.println("minimum value is "+min);
	}

}
