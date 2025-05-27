package CompositionChallenge;

public class Lamp {
	private String style;
	private boolean battery;
	private int globRating;
	public Lamp(String style, boolean battery, int globRating) {
		super();
		this.style = style;
		this.battery = battery;
		this.globRating = globRating;
	}
	
	public void turnOn()
	{
		System.out.println("Light is being turned on");
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void isBattery(boolean battery) {
		this.battery = battery;
	}

	public void setGlobRating(int globRating) {
		this.globRating = globRating;
	}
	
	

}
