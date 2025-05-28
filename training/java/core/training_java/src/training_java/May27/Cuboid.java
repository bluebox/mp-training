package training_java.May27;

public class Cuboid extends Rectangle {
	double height;
	public Cuboid(double length,double width,double height) {
		super(length,width);
		this.height= height< 0? 0 : height;
	}
	public double getHeight() {
		return height;
	}
	public double getVolume() {
		return super.getArea()*height;
	}
public static void main(String[] args) {
	Rectangle r=new Rectangle(5,10);
	Cuboid c=new Cuboid(5,10,5);
	System.out.println("length "+r.getLength()+" breadth "+ r.getWidth()+" area "+ r.getArea());
	System.out.println("length "+c.getLength()+" breadth "+ c.getWidth()+" height "+c.getHeight()+" area "+ c.getVolume());
	
}
}
