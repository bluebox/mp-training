import java.util.*;
class Point
{
	int x,y;
	Point()
	{
		this.x=0;
		this.y=0;
	}
	
	Point(int x,int y)
	{
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
	public double distance()
	{
		return Math.sqrt(x*x+y*y);
	}
	
	double distance(Point other)
	{
	int dx=this.x-other.x;
	int dy=this.y-other.y;
	return Math.sqrt((dx*dx)+(dy*dy));
	}
	
	double distance(int x,int y)
	{
		int dx=this.x-x;
		int dy=this.y-y;
		return ((dx*dx)+(dy*dy));
	}
}
public class PointClassChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point first=new Point(6,5);
		Point second =new Point(3,1);
		System.out.println("Distance from point (0,0) is:"+first.distance());
		System.out.println("Distance from second:"+first.distance(second));
		System.out.println("distance (2,2):"+first.distance(2,2));
		Point point=new Point();
		System.out.println("Distance()="+point.distance());
		

	}

}
