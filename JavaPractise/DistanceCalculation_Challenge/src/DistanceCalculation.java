
public class DistanceCalculation {
	private int x;
	private int y;
	public DistanceCalculation(int x,int y) {
		this.x=x;
		this.y=y;
	}
	public DistanceCalculation() {
		this(4,5);
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
	public double getDistance() {
        return Math.sqrt(x * x + y * y);
    }
    public double getDistance(double a, double b) {
        return Math.sqrt((x - a) * (x - a) + (y - b) * (y - b));
    }
    public double getDistance(DistanceCalculation point) {
    	int a=point.getX();
    	int b=point.getY();
        return Math.sqrt((x - a) * (x - a) + (y - b) * (y - b));
    }
	

}
