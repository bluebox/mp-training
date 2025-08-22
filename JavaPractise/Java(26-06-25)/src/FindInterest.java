
public class FindInterest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		caluclateInterest(100);
	}
	public static void caluclateInterest(double amount)
	{
		double interest;
		for(double rate=7.5;rate<10;rate+=0.25)
		{
			interest=amount*rate/100;
			System.out.println("Interest for 100$ at the rate "+ rate+" is "+"$"+interest);
		}
	}

}
