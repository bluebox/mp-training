
public class Main {

	public static void main(String[] args) {
		DistanceCalculation first= new DistanceCalculation(6,5);
		DistanceCalculation second= new DistanceCalculation(3,1);
		System.out.println(first.getDistance());
		System.out.println(first.getDistance(second));
		System.out.println(first.getDistance(2,2));
		
		

	}

}
