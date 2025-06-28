
public class Wall {

	public static void main(String[] args) {
		
		WallArea wa =new WallArea(2.0,3.0);
		
		System.out.println(wa);
		System.out.println("Area of Wall :"+wa.area());
		wa.setHeight(50.2);
		wa.setWidth(42.3);
		System.out.println("Area of Wall :"+wa.area());
		
		
	}

}
