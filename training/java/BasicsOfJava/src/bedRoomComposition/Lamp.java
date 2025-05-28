package bedRoomComposition;

public class Lamp
{
	private String style;
	private boolean battery;
	private int globRating;
	
	public Lamp(String style,boolean battery,int globRating) {
		
		this.battery = battery;
		this.style = style;
		this.globRating = globRating;
	}
	public void turnOn() {
		
		System.out.println("The lamp is turned on");
	}
	public boolean isBattery() {
		
		return battery;
	}
	public int getGlobRating() {
		
		return globRating;
	}
	
	public String getStyle() {
		
		return style;
	}
}
