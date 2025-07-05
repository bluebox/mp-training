import java.util.Scanner;

public class ReadingUserInputChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		userInput();

	}
	public static void userInput()
	{
		Scanner sc=new Scanner(System.in);
		String num;
		int count=0;
		while(true)
		{
		try
		{
			count++;
			System.out.println("Enter the number #"+count);
			num=sc.nextLine();
			int num1=Integer.parseInt(num);
			if(count==5)
				break;
		}
		catch(NumberFormatException nfe)
		{
			System.out.println("Invalid number");
			break;
		}
	}
	}

}

