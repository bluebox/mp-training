package oops.circleRedi;

public class Cylinder extends Circle {
	
	private double height;
	public Cylinder(double redius,double height) {
		super(redius);
		this.height=height;
	}
	
	public void getCylinderArea() {
		double area=2*(3.14)*getRedius()*height;
		System.out.println("Area of Cylinder : "+String.format( "%.2f", area));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cylinder cy=new Cylinder(3.14, 7);
		System.out.println("Circle Redius : "+cy.getRedius());
		cy.circleArea();
		cy.getCylinderArea();
		
	}

}
