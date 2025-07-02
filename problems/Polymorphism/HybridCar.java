package Polymorphism;

public class HybridCar extends Car{
	
	private double avgKmPerLitre;
    private int cylinder;
    private double avgKmPerCharge;
    private int batterySize;
   
	 public HybridCar(String description, double avgKmPerLitre, int cylinder, double avgKmPerCharge, int batterySize) {
		super(description);
		this.avgKmPerLitre = avgKmPerLitre;
		this.cylinder = cylinder;
		this.avgKmPerCharge = avgKmPerCharge;
		this.batterySize = batterySize;
	}
	 
	 
	 public HybridCar() {
		 
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
       
 	public double getAvgKmPerCharge() {
		return avgKmPerCharge;
	}
	public void setAvgKmPerCharge(double avgKmPerCharge) {
		this.avgKmPerCharge = avgKmPerCharge;
	}
	public int getBatterySize() {
		return batterySize;
	}
	public void setBatterySize(int batterySize) {
		this.batterySize = batterySize;
	}
	
	@Override
	public void startEngine() {
		   System.out.println("The Engine of the Hybrid Car is Running with "+avgKmPerLitre+" avg Km per Litre" +avgKmPerCharge+" avg km per charge");
	   }
	@Override
	   public void drive() {
		   System.out.println("The Hybrid Car has Started");
		   runEngine();
	   }
	@Override
	   protected void runEngine() {
		   System.out.println("The Hybrid Car Engine is running");
	   }
	
	
	
}
