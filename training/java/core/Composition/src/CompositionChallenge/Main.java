package CompositionChallenge;

public class Main {

	public static void main(String[] args) {
		Wall wall1 = new Wall("East");
		Wall wall2 = new Wall("West");
		Wall wall3 = new Wall("North");
		Wall wall4 = new Wall("South");
		Celling celling = new Celling(12, 55);
		Bed bed=new Bed("fancy",2,3,2,1);
		Lamp lamp=new Lamp("Classic", false,75);
		Bedroom bedroom =new Bedroom("Manoj",wall1,wall2,wall3,wall4,celling,lamp,bed);
		bedroom.makeBed();
		bedroom.getlamp().turnOn();
		
				

	}

}
