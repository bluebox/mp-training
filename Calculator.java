package Day3;

class name{
	public static void main(String [] args) {
		
		Floor floor=new Floor(20,10);
		Carpet carpet=new Carpet(20);
	    Calculator calci=new Calculator(floor,carpet);
	    System.out.print(calci.getTotalCost());
}
}

public class Calculator{
	
	private Floor floor;
	private Carpet carpet;
	
	
	
	public Calculator(Floor floor, Carpet carpet) {
		this.floor = floor;
		this.carpet = carpet;
	}
	
	public double getTotalCost() {
		return floor.getArea()*carpet.getCost();
	}

}

class Floor {
    
	private double width;
	private double length;
	public Floor() {
		
	}
	
	public Floor(int width,int length) {
		if(width<0) {
			this.width=0;
		}else{this.width=width;}
		
		if(length<0){
			this.length=0;
		}else{this.length=length;}
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}
	
	public double getArea(){
		return (double)width*length;
	}
}

class Carpet{
	private double cost;
	
	public Carpet(double cost) {
		if(cost>=0) {
			this.cost=cost;
		}
	}
	
	public double getCost() {
		return cost;
	}
	
	
}
