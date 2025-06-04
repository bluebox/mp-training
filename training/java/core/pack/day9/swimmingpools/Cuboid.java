package day9.swimmingpools;

public class Cuboid extends Rectangle{
private double height;

Cuboid(double width,double length,double height){
  super(width,length);
  this.height=height<0?0:height;
}
public double getHeight() {
	return this.height;
}
public double getVolume() {
	return this.height*getArea();
}
}
