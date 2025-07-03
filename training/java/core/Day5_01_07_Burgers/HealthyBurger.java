package Day5_01_07_Burgers;

public class HealthyBurger extends Hamburger{
	 private final int healthyMaxAdditions = 2;
	    private int healthyAdditionsCount = 0;

	    public HealthyBurger(String meat, double price) {
	        super("Healthy", meat, price, "Brown Rye"); 
	    }

	    public boolean addHealthyAddition(String name, double price) {
	        if (healthyAdditionsCount >= healthyMaxAdditions) {
	            System.out.println("Cannot add more than " + healthyMaxAdditions + " healthy additions.");
	            return false;
	        }
	        healthyAdditionsCount++;
	        return super.addAddition(name, price);
	    }
}