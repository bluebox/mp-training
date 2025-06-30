package corejava.june27_Constructors;

public class CalculatorPoJo {
	private FloorPoJo floor;
	private CarpetPoJo carpet;
	
	public CalculatorPoJo(FloorPoJo f,CarpetPoJo c) {
		this.floor=f;
		this.carpet=c;
	}
	
	public double getTotalCost() {
		return floor.getArea()*carpet.getCost();
	}
	
}
