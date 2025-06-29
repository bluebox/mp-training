
public class Challenge9 {
	public static void calculateInterest(int money,int time)
	{
		double interest=0;
		for(double i=7.5 ;i<=10;i+=0.25)
		{
			interest=((money)*(i)*time)/100;
			System.out.println("Interest of money $"+money+" for "+time+" months is "+interest);
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		calculateInterest(100,10);
		

	}

}
