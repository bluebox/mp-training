package CompositionChallenge;

public class Bed {
	private String style;
	private int pillows;
	private int height;
	private int sheets;
	private int qulit;
	public Bed(String style, int pillows, int height, int sheets, int qulit) {
		super();
		this.style = style;
		this.pillows = pillows;
		this.height = height;
		this.sheets = sheets;
		this.qulit = qulit;
	}
	
	public void make()
	{
		System.out .println("BED IS BEING MADE");
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

	public int getQulit() {
		return qulit;
	}
	

}
