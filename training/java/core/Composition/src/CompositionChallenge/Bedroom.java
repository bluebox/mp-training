package CompositionChallenge;

public class Bedroom {
	private String name;
	Wall wall1,wall2,wall3,wall4;
	Celling celling;
	Lamp lamp;
	Bed bed;
	public Bedroom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Celling celling, Lamp lamp, Bed bed) {
		super();
		this.name = name;
		this.wall1 = wall1;
		this.wall2 = wall2;
		this.wall3 = wall3;
		this.wall4 = wall4;
		this.celling = celling;
		this.lamp = lamp;
		this.bed = bed;
	}
	
	public Lamp getlamp()
	{
		return lamp;
	}
	public void makeBed()
	{
		bed.make();
	}
	

}
