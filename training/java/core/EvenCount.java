class EvenCount
{
public static boolean isEvenNumber(int n)
	{
		if(n%2==0)
		{
			return true;
		}
		return false;
	}
	public static void main (String[] args) 
	{
		int i=5;
		int evencount=0;
		while(i<=20)
		{
			if(isEvenNumber(i))
			{
				System.out.println(i);
				evencount++;
				if(evencount==5)
				{
					break;
				}
			}
			i++;
		}
	}
}