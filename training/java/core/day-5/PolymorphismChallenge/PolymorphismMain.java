package PolymorphismChallenge;

public class PolymorphismMain {
public static void main(String []args)
{
//	Car c1=new Car("generic");
//	c1.startEngine();
//	c1.drive();
//	System.out.println();
//	GasPowderClass gpc= new GasPowderClass("ford",15.5,5);
//	gpc.startEngine();
//	gpc.drive();
	Car[] cars=new Car[3];
	cars[0]=new GasPowderClass("honda",15.6,5);
	cars[1]=new ElectricCar("nexon",18.7,7);
	cars[2]=new HybridCar("suziki",16.8,6,9);
	for(Car c:cars)
	{
		System.out.println("Processing car discription");
		c.startEngine();
		c.drive();
	}
	
	
}
}
