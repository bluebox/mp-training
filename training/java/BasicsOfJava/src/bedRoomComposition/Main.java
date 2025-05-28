package bedRoomComposition;

public class Main {
	public static void main(String[] args) {
		
		Wall wall1 = new Wall("East");
		Wall wall2 = new Wall("West");
		Wall wall3 = new Wall("North");
		Wall wall4 = new Wall("South");
		
		Ceiling ceiling = new Ceiling(12, 55);
		
		Lamp lamp = new Lamp("Classic", false, 75);
		Bed bed = new Bed("Modern",4 , 3, 2, 1);
		
		BedRoom bedroom = new BedRoom("Sri ", wall1, wall2,	wall3, wall4, ceiling, lamp, bed);
		
		bedroom.makeBed();
		bedroom.getLamp().turnOn();
		
		bedroom.printData();
	}
}
