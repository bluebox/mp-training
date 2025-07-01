package PolymorphismChallenge;

public class GasPowderClass extends Car {

	private double avgKmPerLiter;
	private int cylinders;
	public GasPowderClass(String discription , double avgKmPerLiter ,int cylinders)
	{
		super(discription);
		this.avgKmPerLiter=avgKmPerLiter;
		this.cylinders=cylinders;
	}
	public  void startEngine()
	  {
		super.startEngine();
		  System.out.println(getDiscription()+" engine has started with cylinders "+cylinders);
	  }
	 public void drive()
	  {
		 super.drive();
		  System.out.println(getDiscription()+" is driving with "+cylinders+" Cylinders "+avgKmPerLiter+"avgkm/hr");
		 // runEngine();
	  }
//	 protected void runEngine()
//	  {
//		 
//		  System.out.println(getDiscription()+" gas powered engine is running ");
//	  }
	public double getavgKmPerLiter()
	{
		return avgKmPerLiter;
	}
	public int getcylinders()
	{
		return cylinders;
	}
	
	
}
