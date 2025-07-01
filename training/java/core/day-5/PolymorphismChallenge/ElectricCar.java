package PolymorphismChallenge;

public class ElectricCar  extends Car{
private double avgKmPerCharge;
private int batterySize;

public ElectricCar(String discription,double avgKmPerCharge ,int batterySize )
{
	super(discription);
	this.avgKmPerCharge=avgKmPerCharge;
	this.batterySize=batterySize;
	
}
public  void startEngine()
{
	super.startEngine();
	  System.out.println(getDiscription()+" engine has started with electricity ");
}
public void drive()
{
	 super.drive();
	  System.out.println(getDiscription()+"is driving with batterySize "+batterySize+" with "+avgKmPerCharge+" avgkm/hr");
	  runEngine();
}
//protected void runEngine()
//{
//	 
//	  System.out.println(getDiscription()+" with electricity engine is running ");
//}
public double getavgKmPerCharge()
{
	return avgKmPerCharge;
}
public int getbatterySize()
{
	return batterySize;
}
}
