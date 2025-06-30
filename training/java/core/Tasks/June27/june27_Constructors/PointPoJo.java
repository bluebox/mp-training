package corejava.june27_Constructors;

public class PointPoJo {
	private int x;
	private int y;
	
	
	public PointPoJo() {
		this(0,0);
	}

	public PointPoJo(int x, int y) {
		this.x = x;
		this.y = y;
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
	}
	
	public double getdistance() {
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	
	public double getdistance(PointPoJo p) {
		int xd=p.x-this.x;
		int yd=p.y-this.y;
		return Math.sqrt(xd*xd+yd*yd);
	}
	
	public double getdistance(int x,int y) {
		int xd=x-this.x;
		int yd=y-this.y;
		return Math.sqrt(xd*xd+yd*yd);
	}
	
}
