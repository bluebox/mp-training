class Floor
{
	private double length,width;
	Floor(double length,double width)
	{
		if(length<0)
			this.length=0;
		else
			this.length=length;
		
		if(width<0)
			this.width=0;
		else
			this.width=width;
	}
	
	double getArea()
	{
		return width*length;
	}
}

class Carpet
{
     double cost;
	
     Carpet(double cost)
     {
    	if(cost<0)
    		this.cost=0;
    	else
    		this.cost=cost;
     }
     
     double getCost()
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
		this.carpet=carpet;
		this.floor=floor;
	}
	
	double getTotalCost()
	{
		return floor.getArea()+ carpet.getCost();
	}
}
public class CarpetExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Carpet c=new Carpet(3.5);
		Floor f=new Floor(2.75,4.0);
		Calculator cal = new Calculator(f,c);
		System.out.println("tital cost: "+ cal.getTotalCost());

	}

}
