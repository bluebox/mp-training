package day_3_june27_oops_principles;

public class WallAreaComputatation {
	private int width,height;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width<=0 ? 0 : width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height<=0 ? 0 : height;
	}
	
	public int getArea() {
		return height*width;
	}
}
