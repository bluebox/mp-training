package Day3;

public class Point{
     int x;
     int y;
     
     public Point() {
    	 
     }
     
     public Point(int x,int y) {
    	 this.x=x;
    	 this.y=y;
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
		return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
	}
	
	public double distance(Point p) {
		return Math.sqrt(Math.pow(x-p.x,2)+Math.pow(y-p.y,2));
	}
	
	public double distance(int x,int y) {
		return Math.sqrt(Math.pow(this.x-x,2)+Math.pow(this.y-y,2));
	}
	
	
     
     
}
