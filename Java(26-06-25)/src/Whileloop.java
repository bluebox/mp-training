
public class Whileloop {

	public static void main(String[] args) {
		int i=5;
		int count=0;
		while(i<=20)
		{
			if(isEven(i))
			{
				count++;
				System.out.println(i);
			}
			if(count==5)
				break;
			i++;
		}
		// TODO Auto-generated method stub

	}
	public static boolean isEven(int num)
	{
		if(num%2==0)
			return true;
		else
			return false;
	}
}

