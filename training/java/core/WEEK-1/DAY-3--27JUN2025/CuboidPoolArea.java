class Rectangle {
 private double width;
 private double length;

 public Rectangle(double width, double length) {
     this.width = width < 0 ? 0 : width;
     this.length = length < 0 ? 0 : length;
 }

 public double getWidth() {
     return width;
 }

 public double getLength() {
     return length;
 }

 public double getArea() {
     return width * length;
 }
}

class Cuboid extends Rectangle {
	private double height;

	public Cuboid(double width, double length, double height) {
		super(width, length);
		this.height = height < 0 ? 0 : height;
	}

	public double getHeight() {
		return height;
	}

	public double getVolume() {
		return getArea() * height;
	}
}

public class CuboidPoolArea{
    public static void main(String[] args) {
        Cuboid cuboid = new Cuboid(5, 10, 15);
        System.out.println("Cuboid Area: " + cuboid.getArea());
        System.out.println("Cuboid Volume: " + cuboid.getVolume());
        System.out.println("Cuboid Width: " + cuboid.getWidth());
        System.out.println("Cuboid Length: " + cuboid.getLength());
        System.out.println("Cuboid Height: " + cuboid.getHeight());
        
    }
}