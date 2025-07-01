package Shapes;

public class ShapesMain {
public static void main(String []args)
{
	Rectangle rct=new Rectangle(5,10);
	System.out.println("rectangle width:" +rct.getWidth());
	Cuboid cbd=new Cuboid(5,10,4);
	System.out.println("cuboid height: "+cbd.getHeight());
	System.out.println("cuboid area: "+cbd.getArea());
	System.out.println("cuboid volume: "+cbd.getVolume());
	
}
}
