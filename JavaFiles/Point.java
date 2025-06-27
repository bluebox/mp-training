
public class Point {
	
	private int x;
	private int y;
	
	public Point() {
		this.x=0;
		this.y=0;
	}

	public Point(int x, int y) {
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
	
	public double distance() {
		return Math.sqrt((x*x)+(y*y));
	}
	
	public double distance(Point po) {
		
		int dx= this.x-po.x;
		int dy= this.y-po.y;
		
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public double distance(int x,int y) {
		int dx=this.x-x;
		int dy=this.y-y;
		return Math.sqrt(dx*dx+dy*dy);
	}


	public static void main(String[] args) {
		
		Point first = new Point(6,5);
		Point second = new Point(3,1);
		
		System.out.println("distance(0,0)= "+first.distance());
		System.out.println("distance(second)= "+first.distance(second));
		System.out.println("distance(2,2)= "+first.distance(2,2));
		Point point=new Point();
		System.out.println("distance()= "+point.distance());




	}

}
