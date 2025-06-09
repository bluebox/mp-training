package Tasks.composition;

public class Bed {
	
	private String style;
	private int pillows;
	private int height;
	private int sheets;
	private int quilt;
	
	public Bed(String style, int pillows, int height, int sheets, int quilt) {
		this.style=style;
		this.sheets=sheets;
		this.pillows=pillows;
		this.height= height;
		this.quilt=quilt;
	}
	
	public void make() {
		System.out.println("Bed -> Making | ");
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

}
