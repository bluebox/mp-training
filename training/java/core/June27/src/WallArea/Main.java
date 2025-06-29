package WallArea;

public class Main {
	public static void main (String[] args) {
		
		Wall wall = new Wall();
		System.out.println("Area of Wall contain Width "+ wall.getWidth()+ " height "+wall.getHeight()+ " is "+wall.getArea());
		
		Wall newWall = new Wall(3,5);
		System.out.println("Area of Wall contain Width "+ newWall.getWidth()+ " height "+newWall.getHeight()+ " is "+newWall.getArea());
	
		Wall wall2 = new Wall(-3,5);
		System.out.println("Area of Wall contain Width "+ wall2.getWidth()+ " height "+wall2.getHeight()+ " is "+wall2.getArea());
	}
}
