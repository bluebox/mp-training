package Polymorphism;

public class Car {
  private String description;
  
  public String getDescription() {
	  return description;
  }
  
  public void setDescription(String string) {
	  this.description=string;
  }
    
   public Car(String description) {
	this.description = description;
}

   public Car() {
		
	} 
   
public void startEngine() {
	   System.out.println("The Engine of the car is Running");
   }
   
   public void drive() {
	   System.out.println("The Car has Started");
	   runEngine();
   }
   
   protected void runEngine() {
	   System.out.println("The Engine is running");
   }
  
  
  
  
  
  
}
