package corejava.june27_Constructors;

public class WallPoJo {
	private double width;
	private double height;
	
	public WallPoJo() {
		this(0,0);
	}
	
	public WallPoJo(double width,double height) {
		if(width<0) 
			this.width=0;
		else
			this.width=width;
		if(height<0)
			this.height=0;
		else
			this.height=height;
	}
	
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getArea() {
		return (this.width)*(this.height);
	}
}
