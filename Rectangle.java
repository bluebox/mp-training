public class Rectangle {
    private double width=0;
    private double length=0;

    public Rectangle(double width,double length){
        this.width=width;
        this.length=length;
        if(this.width<0)this.width=0;
        if(this.length<0)this.length=0;

    }
    public double getWidth() {
        return width;
    }
    public double getLength() {
        return length;
    }
    public double getArea(){
        return width*length;
    }
    
}
