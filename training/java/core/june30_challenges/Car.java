package june30_collections;

public class Car {
	public String description;
	
	Car(String description){
		this.description=description;
		
	}
	
	public void startEngine() {
		System.out.println(description+"engine is started");
		
	}
	
	public void drive() {
		System.out.println(description+"is now driving");
		
	}
	
	protected void runEngine() {
		System.out.println(description+"engine is running.");
	}
	
	
}
