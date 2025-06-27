import java.util.*;
public class Challenge16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		Scanner s=new Scanner(System.in);
		while(true)
		{
			String str=s.nextLine();
			try {
				int number=Integer.parseInt(str);
				if(number<min)
				{
					min=number;
				}
				if(max<number)
					max=number;
			}
			catch(NumberFormatException e)
			{
				System.out.println("You entered a wrong number");
				System.out.println("Minimum number is "+min+" Maximum number is "+max);
			}
		}
	}

}
