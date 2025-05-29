package AbstractionChallenge;
interface Shape {
	double getArea();
}
class Circle implements Shape{
	public double radius;
	public Circle(double radius) {
		this.radius=radius;
	}
	
	@Override
	public double getArea() {
		return this.radius*Math.PI;
	}
}
class Rectangle implements Shape{
    public double length,breadth;
    public Rectangle(double len,double bre) {
    	this.breadth=bre;
    	this.length=len;
    }
	@Override
	public double getArea() {
		return this.length*this.breadth;
	}
	
}
public class InterfaceChallenge {

	public static void main(String[] args) {
		Circle circle =new Circle(30);
		System.out.println(circle.getArea());
		Rectangle rect=new Rectangle(10,24.3);
		System.out.println(rect.getArea());
	}

}
