
public class Car {
     public String description;
     public void startEngine() {
    	 System.out.print(this.getClass().getSimpleName());
     }
     public void drive() {
    	 System.out.print("car is in drive mode");
     }
     public void runEngine() {
    	 System.out.print("car is running");
     }
}
