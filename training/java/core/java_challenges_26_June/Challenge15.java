import java.util.*;
public class Challenge15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum=0,count=0;
		
		Scanner s=new Scanner(System.in);
		
		
		while(true)
		{
			System.out.println("Enter any Integer");
			String str=s.nextLine();
			
			try {
				int number=Integer.parseInt(str);
				sum+=number;
				count++;
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid Number");
				System.out.println("Sum of the numbers is "+sum);
				System.out.println("Average of the numbers is "+ (double)((sum)/count));
			}
		}
		
	}

}
