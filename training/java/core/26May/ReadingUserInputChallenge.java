
public class ReadingUserInputChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int counter=0;
		int sum=0;
		do
		{
			String s=System.console().readLine("Enter Number #"+(counter+1)+":");
			try
			{
				int num=Integer.parseInt(s);
				sum+=num;
				counter++;
			}
			catch(Exception e)
			{
				System.console().printf("Invalid Number\n");
			}
			
		}while(counter<5);
		
		
		System.console().printf("Sum of 5 numbers is :"+sum+"\n");
	}

}
