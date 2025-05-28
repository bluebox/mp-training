
public class HybridCar extends Car{
  public double avgKmPerLitre;
  public int batterySize ;
  public int cyclinder;
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
