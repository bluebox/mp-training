

public class Main{

	public static void main(String[] args) {
		Cylinder c=new Cylinder(5.437,6.436);
		System.out.printf("%.2f\n",c.getRadius());
		System.out.printf("%.2f\n",c.area());
		System.out.printf("%.2f\n",c.getHeight());
		System.out.printf("%.2f\n",c.volume());
	}
}
