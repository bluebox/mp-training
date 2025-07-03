interface B1
{
	public void credit();
	public void debit();
	public void balance();
	public void loan();
	
}
abstract class B2 implements B1
{
	public void credit()
	{
		System.out.println("Credited amount was :"+100);
	}
	
}

abstract class B3 extends B2{
	public void debit()
	{
		System.out.println("Debited amount was: "+50);
	}
}
 class B4 extends B3
{
	public void balance()
	{
		System.out.println("Remaining amount was :"+(100-50));
	}
	public void loan()
	{
		
	}
}
public class InterfaceExample3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B4 b=new B4();
		b.credit();
		b.debit();
		b.balance();
		

	}

}
