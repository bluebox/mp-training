package oops;

public class Carpet {
	private double cost;
	
	Carpet(double cost){
		this.cost = (cost < 0) ? 0 : cost;
	}
	
	public double getCost() {
        return cost;
    }
}
