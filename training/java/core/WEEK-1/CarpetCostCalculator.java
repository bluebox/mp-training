class Carpet {
	double cost;
	
	public Carpet(double cost) {
		this.cost = cost<0 ? 0 : cost;
	}
	
	public double getCost() {
		return this.cost;
	}
}

class Floor {
	double length;
	double width;
	
	public Floor(double length, double width) {	
		this.length = (length < 0) ? 0 : length;
		this.width = (width < 0) ? 0 : width;
	}
	
	
	public double getArea() {
		return length*width;
	}
	
	
}

public class CarpetCostCalculator {
	Floor floor;
	Carpet carpet;
	
	public CarpetCostCalculator(Floor floor, Carpet carpet) {
		this.floor = floor;
		this.carpet = carpet;
	}
	
	public double getTotalCost() {
		return floor.getArea() * carpet.getCost();
	}
	
	
}