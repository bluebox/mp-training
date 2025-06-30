package corejava.june27_Inheritence;

public class PoolVolume {

	public static void main(String[] args) {
		Rectangle rectangle=new Rectangle(5,10);
		System.out.println("rectangle.width= "+rectangle.getWidth());
		System.out.println("rectangle.length= "+rectangle.getLength());
		System.out.println("rectangle.area= "+rectangle.area());
		Cuboid cuboid=new Cuboid(5,10,5);
		System.out.println("cuboid.width= "+cuboid.getWidth());
		System.out.println("cuboid.length= "+cuboid.getLength());
		System.out.println("cuboid.area= "+cuboid.area());
		System.out.println("cuboid.height= "+cuboid.getHeight());
		System.out.println("cuboid.volume= "+cuboid.getVolume());
	}

}
