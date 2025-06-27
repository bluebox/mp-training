
public class EvenNumberChallenge {
public static boolean isEven(int number)
{
	if(number%2==0)
		return true;
	else
		return false;
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0;
		int sum=0;
		int i=5;
		while(i<=20)
		{
			if(isEven(i))
			{
				System.out.println(i+ " is Even Number");
				count++;
				sum+=i;
				if(count==5)
					break;
				
			}
			i++;
		}
		System.out.println("Total sum of Even number is "+ sum);

	}

}
