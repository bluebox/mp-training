
public class CarpetCost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Carpet carpet=new Carpet(3.5);
		Floor floor=new Floor(2.75,4.0);
		Calculator c1=new Calculator(floor,carpet);
		System.out.println("total= "+c1.getTotalCost());
		carpet=new Carpet(1.5);
		floor=new Floor(5.4,4.5);
		c1=new Calculator(floor,carpet);
		System.out.println("total= "+c1.getTotalCost());
	}

}
class Floor
{
	private double width;
	private double length;
	Floor(double width,double length)
	{
		this.width  = (width  < 0) ? 0 : width;
	    this.length = (length < 0) ? 0 : length;
	}
	public double getArea()
	{
		return width*length;
	}
}
class Carpet
{
	private double cost;
	Carpet(double cost)
	{
		this.cost = (cost < 0) ? 0 : cost;
	}
	public double getCost()
	{
		return cost;
	}
}
class Calculator
{
	Floor floor;
	Carpet carpet;
	Calculator(Floor floor,Carpet carpet)
	{		
		this.floor=floor;
		this.carpet=carpet;
	}
	public double getTotalCost()
	{
		return floor.getArea()*carpet.getCost();
	}
}
