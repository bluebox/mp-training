package corejava.june27_Constructors;

public class CarpetPoJo {
	private double cost;
	
	public CarpetPoJo(double cost) {
		if(cost<0)
			this.cost=0;
		else
			this.cost=cost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
