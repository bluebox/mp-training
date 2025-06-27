
public class IsDivisibleBy3And5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int sum=0;
		int count=0;
		for(int i=1;i<=100;i++)
		{
			if(i%3==0 && i%5==0)
			{
				sum+=i;
				count++;
				System.out.println(i+" divided by both 3 and 5");
				
				if(count==5)
					break;
			}
			
		}
		System.out.println("Sum is "+sum);

	}

}
