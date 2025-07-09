package day_3_june27_oops_principles;

public class Carpet {
	private double cost;

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost>=0 ?cost:0;
	}

	public Carpet(double cost) {
		super();
		this.cost = cost;
	}
	
}
