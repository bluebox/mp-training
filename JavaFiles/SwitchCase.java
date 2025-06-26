
public class SwitchCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Switch(1);
		Switch(2);
		Switch(3);
		Switch(6);
		
	}
	
	public static void Switch(int num) {
		switch (num) {
		case 1:
			System.out.println("One");
			break;
		case 2:
			System.out.println("Two");
			break;
		case 3: case 4: case 5:
			System.out.println("3 or 4 or 5");
			break;
		default :
			System.out.println("Default");
		}
	}
	
}
