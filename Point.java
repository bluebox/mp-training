import java.lang.Math;
public class Point {
	private int x;
	private int y;
	public Point() {
		// TODO Auto-generated
	}
	public Point(int x,int y) {
		this.setX(x);
		this.setY(y);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	};
	public double distance() {
		return Math.sqrt(x*x + y*y);
	}
	public double distance(Point point) {
		return Math.sqrt(Math.abs((x-point.x)*(x-point.x)) + Math.abs((point.y-y)*(point.y-y)));
	}
	public double distance(int x,int y) {
		return Math.sqrt(Math.abs((this.x-x)*(this.x-x)) + Math.abs((this.y-y)*(this.y-y)));
	}

}
