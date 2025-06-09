package bedroomCompositionExercise;

public class Bed {
	private String style;
	private int pillows;
	private int height;
	private int sheets;
	private int quilt;
	Bed(String style, int pillows, int height, int sheets, int quilt){
		this.style=style;
		this.pillows=pillows;
		this.height=height;
		this.sheets=sheets;
		this.quilt=quilt;
	}
	void make() {
		System.out.println("The bed is being made");
	}
	String getStyle() {
		return this.style;
	}
	int getPillows() {
		return this.pillows;
	}
	int getHeight() {
		return this.height;
	}
	int getSheets() {
		return this.sheets;
	}
	int getQuilt() {
		return this.quilt;
	}
}
