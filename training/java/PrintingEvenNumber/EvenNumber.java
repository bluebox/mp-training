package PrintingEvenNumber;

public class EvenNumber {

	public static void main(String[] args) {
		int i=5,evenCount=0,oddCount=0;
		while(i<=20)
		{
			if(isEvenNumber(i))
			{
				evenCount++;
				System.out.print(i+ " ");
				if(evenCount == 5)
				{
					System.out.println();

					break;
				}
			}
			else
			{
				oddCount++;
			}
			i++;
		}
		System.out.println("the count of odd numbers is "+oddCount+ " the count of even numbers is "+evenCount);

	}
	public static boolean isEvenNumber(int num)
	{
		return num%2==0;
	}

}
