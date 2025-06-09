package bedroomCompositionExercise;

public class Lamp {
	private String style;
	private boolean battery;
	private int globRating;
	Lamp(String style, boolean battery, int globRating){
		this.style=style;
		this.battery=battery;
		this.globRating=globRating;
	}
	void turnOn() {
		System.out.println("Lamp is being turned on");
	}
	String getStyle() {
		return this.style;
	}
	boolean isBattery() {
		return this.battery;
	}
	int getGlobRating() {
		return this.globRating;
	}
}
