
public class Cylinder extends Circle{
private double height;
double getHeight() {
	return height;
}

double getVolume() {
	return super.getArea(getRadius())*this.height;
}

public Cylinder(double radius,double height) {
	super(radius);
	this.height=(height<0)?0:height;
}
}
