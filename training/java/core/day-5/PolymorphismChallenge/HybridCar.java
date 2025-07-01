package PolymorphismChallenge;

public class HybridCar extends Car{
 private double avgKmPerLiter;
  private int batterySize;
  private int cylinders;
  public HybridCar(String discription,double avgKmPerLiter,int batterySize,int cylinders )
  {
	  super(discription);
	  this.avgKmPerLiter=avgKmPerLiter;
	  this.batterySize=batterySize;
	  this.cylinders=cylinders;
	   
  }
  public  void startEngine()
  {
  	super.startEngine();
  	  System.out.println(getDiscription()+" engine has started with electricity of battery size"+batterySize+" with "+cylinders+"cylinders "+avgKmPerLiter+"  avgkm/ltr");
  }
  public void drive()
  {
  	 super.drive();
  	  System.out.println(getDiscription()+"is driving with electricityand  batterySize "+batterySize+" with "+avgKmPerLiter+" avgkm/ltr");
  	  runEngine();
  }
  public  double getAvgKmPerLiter()
  {
	  return   avgKmPerLiter;
	  
  }
  public int getBatterySize()
  {
	  return  batterySize;
  }
  public int getCylinders()
  {
	  return cylinders;
  }
}
