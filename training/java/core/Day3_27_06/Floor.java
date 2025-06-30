package Day3_27_06;

public class Floor{
	private int width;
	private int length;
	public Floor(int width, int length) {
		if(width<0) {
			this.width=0;
		}else {
		this.width = width;
		}if(length<0) {
			this.length=0;
		}else {
		this.length = length;
		}
	}
	public double getArea() {
		return this.length*this.width;
	}
	
}
