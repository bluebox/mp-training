package Tasks;

public class Point {
	
	private int x;
	private int y;
	
	
	public Point() {
		
	}
	
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
		
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int setX(int x) {
		return this.x=x;
	}
	public int setY(int y) {
		return this.y=y;
	}
	
	public double distance() {
		return distance(0,0);
	}
	
	public double distance(Point point) {
		return distance(point.x, point.y);
	}
	
	public double distance(int x, int y) {
		return Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y, 2)); 
		
	}

}
