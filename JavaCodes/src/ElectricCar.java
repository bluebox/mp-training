
public class ElectricCar extends Car {
  public double avgKmPerCharge;
  public int batterySize ;
  public void drive() {
	  System.out.println(this.getClass().getSimpleName()+" is in drive");
  }
  public void startEngine() {
	  System.out.println(this.getClass().getSimpleName()+" is start");
  }
  public void runEngine() {
	  System.out.println(this.getClass().getSimpleName()+" is in run");
  }
}
