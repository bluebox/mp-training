
public class Main {

	public static void main(String[] args) {
		Rectangle rec =new Rectangle(5,10);
		System.out.println(rec.getLength());
		System.out.println(rec.getWidth());
		System.out.println(rec.getLength());
		
		Cuboid cub =new Cuboid(5,10,15);
		System.out.println(cub.getLength());
		System.out.println(cub.getHeight());
		System.out.println(cub.getWidth());
		System.out.println(cub.getVolume());

	}

}
