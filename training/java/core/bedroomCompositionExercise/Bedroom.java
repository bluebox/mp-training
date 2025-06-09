package bedroomCompositionExercise;

public class Bedroom {
	String name;
	Wall wall1, wall2, wall3, wall4;
	Ceiling ceiling;
	Bed bed;
	Lamp lamp;
	Bedroom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Ceiling ceiling, Bed bed, Lamp lamp){
		this.name=name;
		this.wall1=wall1;
		this.wall2=wall2;
		this.wall3=wall3;
		this.wall4=wall4;
		this.ceiling=ceiling;
		this.bed=bed;
		this.lamp=lamp;
	}
	Lamp getLamp() {
		return this.lamp;
	}
	void makeBed() {
		System.out.println("Bed is being made");
		bed.make();
	}
}
