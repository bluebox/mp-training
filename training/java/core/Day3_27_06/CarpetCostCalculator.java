package Day3_27_06;

public class CarpetCostCalculator {
	Carpet cp;
	Floor fl;
	public CarpetCostCalculator(Carpet cp, Floor fl) {
		this.cp = cp;
		this.fl = fl;
	}
	public double getTotalCost() {
		return this.fl.getArea()*this.cp.getCost();
	}
	public static void main(String args[]) {
		
		CarpetCostCalculator price=new CarpetCostCalculator(new Carpet(10),new Floor(10,15));
		System.out.println(price.getTotalCost());
	}
}

