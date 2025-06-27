
public class CostCal {
	
	private Floor floor;
	private Carpet carpet;
	
	public CostCal(Floor floor, Carpet carpet) {
		this.floor = floor;
		this.carpet = carpet;
	}
	
	public double getTotalCost() {
		return floor.getArea()*carpet.getcost();
	}
	

}
