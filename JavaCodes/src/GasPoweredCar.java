
public class GasPoweredCar extends Car {
      public double avgKmPerLitre;
      public int cyclinders;
      public void startEngine() {
    	  System.out.println(this.getClass().getSimpleName()+" is in start");
      }
      public void drive() {
    	  System.out.println(this.getClass().getSimpleName()+" is in drive");
      }
      public void runEngine() {
    	  System.out.println(this.getClass().getSimpleName()+"is in run mode");
      }
}
