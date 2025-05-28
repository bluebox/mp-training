package bedRoomComposition;


public class BedRoom {

	private String name;
	private Wall wall1;
	private Wall wall2;
	private Wall wall3;
	private Wall wall4;
	private Ceiling ceiling;
	private Lamp lamp;
	private Bed bed;
	
	public BedRoom(String name,Wall wall1,	Wall wall2,	Wall wall3,	Wall wall4,	Ceiling ceiling,Lamp lamp, Bed bed) {
		this.name = name;
		this.wall1 = wall1;
		this.wall2 = wall2;
		this.wall3 = wall3;
		this.wall4 = wall4;
		this.ceiling = ceiling;
		this.lamp = lamp;
		this.bed = bed;
	}
	
	public void printData() {
		
		System.out.println("The person named "+name+" got a room with walls dirctions as \n "+wall1.getDirection()+" "
		+wall2.getDirection()+" "+wall3.getDirection()+" "+wall4.getDirection()+" painted ceiling with color #"+ceiling.getPaintedColor()
		+" and it is at a height of "+ceiling.getHeight());
		
	}
	
	public Lamp getLamp() {
		return lamp;
	}
	
	public void makeBed() {
		
		System.out.println("The bed is being made");
		bed.make();
	}
}
