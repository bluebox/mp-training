package day_3_june27_oops_principles;

public class Point {
    private int x, y;

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

    public double distance(Point B) {
        int dx = this.x-B.x;
        int dy = this.y-B.y;
        return Math.sqrt(dx* dx+dy*dy);
    }

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
