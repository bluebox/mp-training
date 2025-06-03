package BedRoomChallenge;

public class BedRoom {
	
	private String name;
	Wall wall1;
	Wall wall2;
	Wall wall3;
	Wall wall4;
	Ceiling ceiling;
	Bed bed;
	Lamp lamp;
	
	

	public BedRoom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Ceiling ceiling, Bed bed, Lamp lamp) {
		
		this.name = name;
		this.wall1 = wall1;
		this.wall2 = wall2;
		this.wall3 = wall3;
		this.wall4 = wall4;
		this.ceiling = ceiling;
		this.bed = bed;
		this.lamp = lamp;
	}
	
	public Lamp  getLamp()
	{
		return this.lamp;
	}

	public void makeBed()
	{
		bed.make();
		
	}
	public static void main(String[] args) {
		Wall wall1=new Wall("west");
		Wall wall2=new Wall("East");
		Wall wall3=new Wall("South");
		Wall wall4=new Wall("North");
		Ceiling ceiling=new Ceiling(12,55);
		Bed bed= new Bed("modern",4,3,2,1);
		Lamp lamp =new Lamp(75,false,"classic");
		BedRoom bedRoom= new BedRoom("Ramsai Rathod",wall1,wall2,wall3,wall4,ceiling,bed,lamp);
		bedRoom.makeBed();
		bedRoom.getLamp().turnOn();

	}

}
class Lamp
{
	private int globalRating;
	private boolean battery;
	private String style;
	
	public Lamp(int globalRating, boolean battery, String style) {
		this.globalRating = globalRating;
		this.battery = battery;
		this.style = style;
	}

	public int getGlobalRating() {
		return globalRating;
	}

	public boolean isBattery() {
		return battery;
	}

	public String getStyle() {
		return style;
	}
	public void turnOn()
	{
		System.out.println("the lamp is turned on");
	}
	public void turnOff()
	{
		System.out.println("the lamp is turned off");
	}
	
}
 
class Bed{
	private String style;
	private int pillows;
	private int height;
	private int sheets;
	private int quilt;
	
	
	public Bed(String style, int pillows, int height, int sheets, int quilt) {
		this.style = style;
		this.pillows = pillows;
		this.height = height;
		this.sheets = sheets;
		this.quilt = quilt;
	}
	public String getStyle() {
		return style;
	}
	public int getPillows() {
		return pillows;
	}
	public int getHeight() {
		return height;
	}
	public int getSheets() {
		return sheets;
	}
	public int getQuilt() {
		return quilt;
	}
	public void make()
	{
		System.out.println("the bed is made");
	}

}

class Ceiling
{
	private int height;
	private int paintedColor;
	
	public Ceiling(int height, int paintedColor) {
		this.height = height;
		this.paintedColor = paintedColor;
	}

	public int getHeight() {
		return height;
	}

	public int getPaintedColor() {
		return paintedColor;
	}
	
	
}

class Wall
{
	private String direction;

	public Wall(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}
	
	
}







