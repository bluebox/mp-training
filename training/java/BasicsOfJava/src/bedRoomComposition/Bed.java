package bedRoomComposition;

public class Bed{
	
	private String style;
	private int pillows;
	private int height;
	private int sheets;
	private int quilt;
	
	public Bed(String style,int pillows,int height,int sheets,int quilt) {
		
		this.height = height;
		this.pillows = pillows;
		this.quilt = quilt;
		this.sheets = sheets;
		this.style = style;
	}
	
	public void make() {
		
		System.out.println("The bed is being made");
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