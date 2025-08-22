
public class Main {

	public static void main(String[] args) {
		    Carpet carpet=new Carpet(2.5);
		    Floor floor=new Floor(2.67, 64.00);
		    Calculator calculator=new Calculator(floor, carpet);
		    System.out.println("total cost"+calculator.gettotalcost());
		    
		     carpet=new Carpet(1.5);
		     floor=new Floor(5.4, 4.5);
		     calculator=new Calculator(floor, carpet);
		    System.out.println("total cost"+calculator.gettotalcost());
	}

}
