
public class FirstAndLastDigits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Sum(257));
	}
	public static int Sum(int n)
	{
		if(n<0)
		{
			return -1;
		}
		int sum=0;
		int n1=n;
		sum+=n%10;
		int count=0;
		while(n!=0)
		{
			n=n/10;
			count++;
		}
		sum+=n1/(Math.pow(10,count-1));
		return sum;
	}

}

