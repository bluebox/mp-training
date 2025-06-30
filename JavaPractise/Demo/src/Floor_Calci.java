
public class Floor_Calci {
	Floor floor;
	Carpet carpet;
	public Floor_Calci(Floor floor, Carpet carpet) {
		this.floor = floor;
		this.carpet = carpet;
	}
	public double getTotalCost() {
		return (floor.getArea() * carpet.getCost());
	}
	

}
