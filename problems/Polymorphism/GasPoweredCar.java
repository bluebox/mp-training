package Polymorphism;

public class GasPoweredCar extends Car {
      private double avgKmPerLitre;
      private int cylinder;
      
     public GasPoweredCar() {
    	 
     }
      
	public GasPoweredCar(String description ,double avgKmPerLitre, int cylinder) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinder = cylinder;
	}
	
	
	public double getAvgKmPerLitre() {
		return avgKmPerLitre;
	}
	public void setAvgKmPerLitre(double avgKmPerLitre) {
		this.avgKmPerLitre = avgKmPerLitre;
	}
	public int getCylinder() {
		return cylinder;
	}
	public void setCylinder(int cylinder) {
		this.cylinder = cylinder;
	}
      
	@Override
	public void startEngine() {
		   System.out.println("The Engine of the GasPowered Car is Running with "+avgKmPerLitre+"avg Km per litre");
	   }
	@Override
	   public void drive() {
		   System.out.println("The Electric Car has Started");
		   runEngine();
	   }
	@Override
	   protected void runEngine() {
		   System.out.println("The GasPowered Car Engine is running");
	   }
	
      
}
