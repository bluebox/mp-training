package corejava.june27_Constructors;

public class WallAreaComputation {
	public static void main(String[] args) {
		WallPoJo obj=new WallPoJo();
		System.out.println(obj.getWidth());
		System.out.println(obj.getHeight());
		System.out.println(obj.getArea());
		obj.setHeight(15);
		obj.setWidth(20);
		System.out.println(obj.getWidth());
		System.out.println(obj.getHeight());
		System.out.println(obj.getArea());
	}
}
