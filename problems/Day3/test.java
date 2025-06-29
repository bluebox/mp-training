package Day3;

public class test {
	public static void main(String [] args) {
		
	Floor floor=new Floor(20,10);
	Carpet carpet=new Carpet(20);
    Calculator calci=new Calculator(floor,carpet);
    System.out.print(calci.getTotalCost());
    
    
    
	}
}
