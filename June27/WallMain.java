package June27;

public class WallMain {
	public static void main(String[] args) {
		
		Wall w1 = new Wall();
		Wall w2 = new Wall(1.0,2.5);
		Wall w3 = new Wall(-1.0,2.0);
		
		System.out.println(w1.getArea());
		System.out.println(w2.getArea());
		System.out.println(w3.getArea());
		
	}
}
