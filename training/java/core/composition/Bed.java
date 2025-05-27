
public class Bed {
private String style;
private int pillows,sheets,height,quilt;

public Bed(String style, int pillows, int sheets, int height, int quilt) {
	this.style = style;
	this.pillows = pillows;
	this.sheets = sheets;
	this.height = height;
	this.quilt = quilt;
}

public int getPillows() {
	return pillows;
}

public int getSheets() {
	return sheets;
}

public int getHeight() {
	return height;
}

public int getQuilt() {
	return quilt;
}
public String getStyle() {
	return style;
}
public void make() {
	System.out.println("Bed is being made.");
}


}
