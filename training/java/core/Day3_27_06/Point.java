package Day3_27_06;

public class Point {
	private int X;
	private int Y;
	public Point(int x, int y) {
		this.X = x;
		this.Y = y;
	}
	public Point() {
		this.X=10;
		this.Y=10;
	}
	public int getX() {
		return X;
	}
	public void setX(int x) {
		X = x;
	}
	public int getY() {
		return Y;
	}
	public void setY(int y) {
		Y = y;
	}
	public double distance() {
		return Math.sqrt(this.X*this.X+this.Y*this.Y);
	}
	public double distance(Point p1) {
		return Math.sqrt(Math.pow(p1.getX()-this.X,2)+Math.pow(p1.getY()-this.Y,2));
	}
	public double distance(int X,int Y) {
		return distance(new Point(X,Y));
	}
	public static void main(String args[]) {
		Point p1=new Point(15,20);
		System.out.println(p1.distance());
		System.out.println(p1.distance(p1));
		System.out.println(p1.distance(10,20));

	}
}
