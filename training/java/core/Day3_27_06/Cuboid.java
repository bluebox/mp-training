package Day3_27_06;

public class Cuboid extends Rectangle {
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
	public static void main(String args[]) {

			Cuboid cuboid = new Cuboid(5.0, 3.0, 2.0);
			System.out.println("Width: " + cuboid.getWidth());
			System.out.println("Length: " + cuboid.getLength());
			System.out.println("Height: " + cuboid.getHeight());
			System.out.println("Area: " + cuboid.getArea());
			System.out.println("Volume: " + cuboid.getVolume());
	}
}