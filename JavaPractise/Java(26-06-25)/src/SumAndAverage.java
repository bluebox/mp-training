import java.util.Scanner;

public class SumAndAverage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inputThenSumAndAverage();
		
	}
public static void inputThenSumAndAverage()
{
	Scanner sc=new Scanner(System.in);
	String num;
	int sum=0;
	int count=0;
	while(true)
	{
	try
	{
		System.out.println("enter the number until loop exits");
		num=sc.nextLine();
		int num1=Integer.parseInt(num);
		sum+=num1;
		count++;
	}
	catch(NumberFormatException nfe)
	{
		System.out.println("we are exiting the loop because its not a number");
		break;
	}
	}
	if(count>0)
	{
		long avg=sum/count;
		System.out.println("SUM = "+sum+" AVG ="+avg);
	}
	else
	{
		System.out.println("SUM = 0 AVG = 0");
	}
}
}