package OOPS;


class Lamp{
	private String style;
	private boolean battery;
	private int globalRating;
	
	public Lamp(String style,boolean battery,int gloabrating) {
		this.style=style;
		this.battery=battery;
		this.globalRating=gloabrating;
	}
	public void turnOn() {
		System.out.println("Turning on the light !");
	}
	public void getStyle() {
		System.out.println("Style of lamp : "+style);
	}
	public void isBattery() {
		System.out.println("battery : "+battery);
	}
	public void getGlobalRating() {
		System.out.println("Rating of the lamp : "+globalRating);
	}
}
class Bed{
	private String Style;
	private int pillows;
	private int height;
	private int sheets;
	private int quilt;
	
	public Bed(String Style,int pillows,int height,int sheets,int quilt) {
		this.Style =Style;
		this.pillows = pillows;
		this.height = height;
		this.sheets = sheets;
		this.quilt = quilt;
		
	}
	public void makeBed() {
		System.out.println("Making the bed!");
	}
	public void getStyle() {
		System.out.println("Style : "+Style);
	}
	public void getpillows() {
		System.out.println("pillows : "+pillows);
	}
	public void getHeight() {
		System.out.println("Height of bed : "+height);
	}
	public void getSheets() {
		System.out.println("No of sheets on the bed : "+sheets);
	}
	public void getQuilt() {
		System.out.println("Quilt : "+quilt);
	}
	
}

class Ceiling{
	private int height;
	private int paintedColor;
	public Ceiling(int height, int paintedColor) {
		this.height = height;
		this.paintedColor = paintedColor;
	}
	public void getHeight() {
		System.out.println("heig of celing : "+height);
	}
	public void getPaintedColor() {
		System.out.println("color of celing "+paintedColor);
	}

	
}
class Wall{
	private String direction;

	public Wall(String direction) {
		this.direction = direction;
	}

	public void getDirection() {
		System.out.println("Direction of wall "+direction);
	}
	
}

class Bedroom{
	private String name ;
	private Wall wall1;
	private Wall wall2;
	private Wall wall3;
	private Wall wall4;
	private Ceiling celling;
	private Bed bed;
	private Lamp lamp;
	
	
	public Bedroom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Ceiling celling, Bed bed, Lamp lamp) {
		this.name = name;
		this.wall1 = wall1;
		this.wall2 = wall2;
		this.wall3 = wall3;
		this.wall4 = wall4;
		this.celling = celling;
		this.bed = bed;
		this.lamp = lamp;
	}
	
	public void getLamp() {
		lamp.getStyle();
	}
	public void makeBed() {
		bed.makeBed();
	}
	
}
public class BedroomComposition {
	
	public static void main(String[] args) {
		Wall wall1 = new Wall("West");
		Wall wall2 = new Wall("East");
		Wall wall3 = new Wall("North");
		Wall wall4 = new Wall("South");
		
		Ceiling ceiling = new Ceiling(12,55);
		
		Bed bed = new Bed("Modren",4,3,2,1);
		Lamp lamp = new Lamp("classic",false,75);
		Bedroom bedRoom = new Bedroom("Saketh Maryala",wall1,wall2,wall3,wall4,ceiling,bed,lamp);
		
		bedRoom.makeBed();
		bedRoom.getLamp();
	}

}