
public class WallArea {
	private double height;
	private double width;
	
	public WallArea() {
		System.out.println("Getting the Area of the wall");
	}
	
	public WallArea(double height, double width) {
		this.height = height;
		this.width = width;
	}
	
	
	
	@Override
	public String toString() {
		return " Height =" + this.height + ", Width=" + this.width;
	}

	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = (height<0 ? 0: height);
		System.out.println("Updagte Height :"+this.height);

	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = (width<0 ? 0 : width);
		System.out.println("Updagte Width :"+this.width);

	}
	
	public double area() {
		return this.height*this.width;
	}

}
