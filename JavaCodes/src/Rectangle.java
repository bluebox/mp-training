
public class Rectangle {
    private int width;
    private int length;
    public Rectangle(int width,int length) {
    	if(width<0)this.width=0;
    	else this.width=width;
    	if(length<0)this.length=0;
    	else this.length=length;
    }
    public int getWidth() {
    	return this.width;
    }
    public int getLenth() {
    	return this.length;
    }
    public int getArea() {
    	return this.width*this.length;
    }
}
