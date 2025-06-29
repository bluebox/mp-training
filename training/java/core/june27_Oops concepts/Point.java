package june27_constructors;
import java.util.Scanner;

public class Point {
    private int x;
    private int y;

    // No-args constructor
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    // Parameterized constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

   
    public void setY(int y) {
        this.y = y;
    }

   
    public double distance() {
        return Math.sqrt((x * x) + (y * y));
    }

   
    public double distance(Point point) {
        return Math.sqrt((point.x - this.x) * (point.x - this.x)
                + (point.y - this.y) * (point.y - this.y));
    }

   
    public double distance(int x, int y) {
        return Math.sqrt((x - this.x) * (x - this.x)
                + (y - this.y) * (y - this.y));
    }

   
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter x for first point: ");
        int x1 = sc.nextInt();
        System.out.print("Enter y for first point: ");
        int y1 = sc.nextInt();

      //Creating first point
        Point first = new Point(x1, y1);

       
        System.out.print("Enter x for second point: ");
        int x2 = sc.nextInt();
        System.out.print("Enter y for second point: ");
        int y2 = sc.nextInt();

        // Create second point
        Point second = new Point(x2, y2);

        // Display distances
        System.out.println("Distance from first point to (0,0) = " + first.distance());
        System.out.println("Distance from second point to (0,0) = " + second.distance());
        System.out.println("Distance between first and second point = " + first.distance(second));

        sc.close();
    }
}
