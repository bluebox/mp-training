package CarpetCostCalculation;

public class Floor {
    private double width;
   private double length;
    public Floor(double width, double length)
    {
    	if(width<0)
    	{
    		this.width=0;
    	}
    	if(length<0)
    	{
    		this.length=0;
    	}
    	this.width=width;
    	this.length=length;
    }
    public double getarea()
    {
    	return this.width*this.length;
    }
}
