package CuboidPool;

public class Calculate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangle rectangle=new Rectangle(5,10);
		System.out.println("Rectangle Width :"+rectangle.getWidth());
		System.out.println("Rectangle Length :"+rectangle.getLength());
		System.out.println("Rectangle Area :"+rectangle.getArea());
		Cuboid cuboid = new Cuboid(5,10,5);
		System.out.println("Cuboid Width :"+cuboid.getWidth());
		System.out.println("Cuboid Length :"+cuboid.getLength());
		System.out.println("Cuboid Height :"+cuboid.getHeight());
		System.out.println("Cuboid Area :"+cuboid.getArea());
		System.out.println("Cuboid Volume :"+cuboid.getVolume());
	}

}
