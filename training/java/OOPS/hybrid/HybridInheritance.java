package hybrid;

public class HybridInheritance {

	public static void main(String[] args) {
		Audi a=new Audi();
		a.printData();
		
		

	}

}
  interface Model
 {
	
	public void sound();
	public void printData();
 }
 class Factory
 {
	 private String facName;
	 private double makingCost;
	public Factory(String facName, double makingCost) {
		this.facName = facName;
		this.makingCost = makingCost;
	}
	@Override
	public String toString() {
		return "name:"+this.facName+" makingCost:"+this.makingCost;
	}
	 
 }
 class Audi extends Factory implements Model
 {
	 public Audi()
	 {
		 super("tesla",3435);
	 }
	 public void sound()
	 {
		 System.out.println("sound");
	 }
	 public void printData() {
		 System.out.println(super.toString());
	 }
 }
