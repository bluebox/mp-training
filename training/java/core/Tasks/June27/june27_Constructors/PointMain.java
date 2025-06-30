package corejava.june27_Constructors;

public class PointMain {

	public static void main(String[] args) {
		PointPoJo p1=new PointPoJo(3,4);
		PointPoJo p2=new PointPoJo(1,2);
		System.out.println(p1.getX());
		System.out.println(p1.getY());
		System.out.println(p1.getdistance());
		System.out.println(p1.getdistance(p2));
		System.out.println(p1.getdistance(2, 3));
		p1.setX(0);
		p1.setY(0);
		System.out.println(p1.getX());
		System.out.println(p1.getY());
		System.out.println(p1.getdistance());
	}

}
