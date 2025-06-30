
public class Rectangle {
	protected double length;
	protected double width;
	
	public Rectangle(double length, double width) {
		if(length<0) this.length=0;
		else this.length = length;
		if(width<0)this.width = 0;
		else this.width=width;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getArea() {
		return length*width;
	}
	

}
