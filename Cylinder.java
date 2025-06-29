public class Cylinder extends Circle{
    private double  height;
    public Cylinder(double radius,double height){
        super(radius);
        this.height=height;
        if(this.height<0)this.height=0;

    }
    public double getHeight() {
        return height;
    }
    public double getVolume(){
        return getArea()*height;
    }
}
