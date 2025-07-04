interface Car1
{
	void drive(int speed,String model);
}

public class LambdaExpressionex2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car1 c1=(speed,model)->{
			if(speed>80)
			{
				System.out.println("Car is going with speed "+ speed + "and it is going very fast, and model is "+model);
			}
			else
			{
				System.out.println("Car is going with speed "+ speed + "and it is going slow, and model is "+model);
			
			}
		};
		c1.drive(100,"BMW");
		c1.drive(30,"Suzuki");

	}

}
