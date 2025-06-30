class Point {
    private int x;
    private int y;

    // No-arg constructor
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    // Constructor with parameters
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getter for x
    public int getX() {
        return this.x;
    }

    // Getter for y
    public int getY() {
        return this.y;
    }

    // Setter for x
    public void setX(int x) {
        this.x = x;
    }

    // Setter for y
    public void setY(int y) {
        this.y = y;
    }

    // Distance to (0, 0)
    public double distance() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    // Distance to another Point
    public double distance(Point other) {
        return Math.sqrt((other.x - this.x) * (other.x - this.x) +
                         (other.y - this.y) * (other.y - this.y));
    }

    // Distance to given x and y coordinates
    public double distance(int x, int y) {
        return Math.sqrt((x - this.x) * (x - this.x) +
                         (y - this.y) * (y - this.y));
    }
}

public class PointClassAndDistanceCalculation {
    public static void main(String[] args) {
        Point first = new Point(6, 5);
        Point second = new Point(3, 1);
        System.out.println("distance(0,0)= " + first.distance());
        System.out.println("distance(second)= " + first.distance(second));
        System.out.println("distance(2,2)= " + first.distance(2, 2));
        Point point = new Point();
        System.out.println("distance()= " + point.distance());
    }
}
