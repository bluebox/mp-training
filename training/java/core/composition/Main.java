
public class Main {

	public static void main(String[] args) {
		//creating wall
		Wall wall1= new Wall("East");
		Wall wall2= new Wall("West");
		Wall wall3= new Wall("South");
		Wall wall4= new Wall("North");
		
		//Ceiling,lamp,bed
		Ceiling ceiling = new Ceiling(25,40);
		Lamp lamp=new Lamp("style1",true,99);
		Bed bed= new Bed("Style2",3,2,1,1);
		
		Bedroom br1 =new Bedroom("Name1",wall1,wall2,wall3,wall4,ceiling,lamp,bed);
		//using methods
		br1.makeBed();
		br1.getLamp().turnOn();
		
	}

}
