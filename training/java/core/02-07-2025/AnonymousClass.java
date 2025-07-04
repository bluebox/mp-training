interface Car
{
	void drive();
}

class Bike implements Car
{
	public void drive()
	{
	System.out.println("This is Bike class");
	}
}
public class AnonymousClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car c1=new Car()
		{
			public void drive()
			{
			System.out.println("This is TATA car");
		    }
				
	};
	
			
    
    c1.drive();

	Bike b1=new Bike();
	b1.drive();

}
}
