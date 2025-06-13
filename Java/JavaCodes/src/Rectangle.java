
public class Rectangle {
    private double width;
    private double length;
    public Rectangle(double width,double length) {
    	if(width<0)this.width=0;
    	else this.width=width;
    	if(length<0)this.length=0;
    	else this.length=length;
    }
    public double getWidth() {
    	return this.width;
    }
    public double getLenth() {
    	return this.length;
    }
    public double getArea() {
    	return this.width*this.length;
    }
}
