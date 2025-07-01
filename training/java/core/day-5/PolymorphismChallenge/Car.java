package PolymorphismChallenge;

public class Car {
  private String discription;
  public Car(String discription)
  {
	  this.discription =discription;
  }
  public  void startEngine()
  {
	  System.out.println(discription+" engine has started");
  }
  public void drive()
  {
	  System.out.println(discription+" is driving");
	  runEngine();
  }
  protected void runEngine()
  {
	  System.out.println(discription+" engine is running");
  }
  public String getDiscription()
  {
	  return discription;
			  
  }
}
