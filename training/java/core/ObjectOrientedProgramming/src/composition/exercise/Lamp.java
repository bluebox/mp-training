package composition.exercise;

public class Lamp {
	private String type;
	private boolean battery;
	private int globRating;
	public Lamp(String type, boolean battery, int globRating) {
		super();
		this.type = type;
		this.battery = battery;
		this.globRating = globRating;
	}
	
	public void turnOn()
	{
		System.out.println("Lamp is turned on!!");
	}
	
	public boolean isBattery()
	{
		return battery;
	}
	
	public int getGlobRating()
	{
		return globRating;
	}
	
	
}
