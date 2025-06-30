package Day3_27_06;

public class WallAreaComputation {
	private double width;
	private double height;
	public WallAreaComputation(double width, double height) {
		this.width = width;
		this.height = height;
	}
	public WallAreaComputation() {
		this.width=10.0;
		this.height=10.0;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		if(width<0) {
			this.width=0;
		}else {
		this.width = width;
		}
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		if(height<0) {
			this.height=0;
		}else {
		this.height = height;
		}
	}
	public double getArea() {
		return this.height*this.width;
	}
	public static void main(String args[]) {
		WallAreaComputation wall1=new WallAreaComputation(15.0,25.0);
		System.out.println(wall1.getArea());
	}
	
}
