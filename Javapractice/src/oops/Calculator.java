package oops;

public class Calculator {
	public static void main(String[] args) {
		Carpet newCarpet= new Carpet(3.5);
		Floor newFloor=new Floor(2.75,4.0);
		Calculator1 calc=new Calculator1(newFloor, newCarpet);
		
		System.out.print(calc.getTotalCost());
	}
}

class Calculator1{
	private Floor floor;
	private Carpet carpet;
	
	public Calculator1(Floor floor,Carpet carpet) {
		this.carpet=carpet;
		this.floor=floor;
	}
	
	public double getTotalCost() {
		return floor.getArea()*carpet.getCost();
	}
}
