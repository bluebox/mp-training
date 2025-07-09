
public class Lambdaexpression {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car a1=new Audi();
		a1.drive(50);
		Car c1=new Car()
				{
					public void drive(int speed)
					{
						System.out.println("drives with speed "+speed);
					}
				};
				c1.drive(100);
//				Car c2=(speed)->{System.out.println("drives with speed "+speed);};
//				c2.drive(150);
				Car c2=(speed)->System.out.println("drives with speed "+speed);
				c2.drive(150);
				
	}

}
class Audi implements Car
{
	public void drive(int speed) {
		// TODO Auto-generated method stub
		System.out.println("drives with speed "+speed);
		
	}
	
	
}
