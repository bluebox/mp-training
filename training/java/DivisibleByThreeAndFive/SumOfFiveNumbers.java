package DivisibleByThreeAndFive;

public class SumOfFiveNumbers {

	public static void main(String[] args) {
		
		int sum=0, counter=0;
		for(int i = 1 ;i <= 1000 ; i++ )
		{
			if(i%3 == 0 && i%5 == 0)
			{
				counter++;
				sum+=i;
				System.out.print(i+ " ");
				if(counter == 5)
				{
					System.out.println();
					break;
				}
			}
			
		}
		System.out.println( "the sum of 5 satisfied values is = "+sum);

	}

}
