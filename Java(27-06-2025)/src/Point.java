 class Point {
	private int x;
	private int y;
	Point()
	{
		System.out.println("No argument Constructor");
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
		return Math.sqrt((x*x)+(y*y));
	}
	public double distance(Point p1)
	{
		int u1=this.x-p1.getX();
		int u2=this.y-p1.getY();
		return Math.sqrt(u1*u1+u2*u2);
	}
	public double distance(int x1,int y1)
	{
		int p1=this.x-x1;
		int p2=this.y-y1;
		return Math.sqrt(p1*p1+p2*p2);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point P1=new Point(6,5);
		Point P2=new Point(3,1);
		System.out.println("distance(0,0)= "+P1.distance());
		System.out.println("distance(second)= "+P1.distance(P2));
		System.out.println("distance(2,2)= "+P1.distance(2,2));
		Point point =new Point();
		System.out.println("distance()= "+point.distance());
	}

}
