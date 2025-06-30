package corejava.june27_Constructors;

public class CarpetCostMain {

	public static void main(String[] args) {
		FloorPoJo floor=new FloorPoJo(2.75,4.0);
		CarpetPoJo carpet=new CarpetPoJo(3.5);
		CalculatorPoJo calc=new CalculatorPoJo(floor,carpet);
		System.out.println("total= "+calc.getTotalCost());
		carpet=new CarpetPoJo(1.5);
		floor=new FloorPoJo(5.4,4.5);
		calc=new CalculatorPoJo(floor,carpet);
		System.out.println("total= "+calc.getTotalCost());
	}

}
