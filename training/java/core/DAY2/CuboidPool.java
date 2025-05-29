package training.java.core.DAY2;

public class CuboidPool {
    public static void main(String[] args) {
        Rectangle rectangle= new Rectangle(5,10);
        System.out.println(rectangle.getWidth());
        System.out.println(rectangle.getLength());
        System.out.println(rectangle.getArea());
        Cuboid cuboid= new Cuboid(5,10,5);
        System.out.println(cuboid.getWidth());
        System.out.println(cuboid.getLength());
        System.out.println(cuboid.getArea());
        System.out.println(cuboid.getHeight());
        System.out.println(cuboid.getVolume());
    }
}
class Rectangle{
    double width;
    double length;
    public Rectangle(double width, double length) {
        if(width>=0){
        this.width = width;    
        }
        if(length>=0){
        this.length = length;  
        }        
    }
    public double getWidth() {
        return width;
    }
    public double getLength() {
        return length;
    }
    public double getArea(){
        return length*width;
    }
}

class Cuboid extends Rectangle{
    double height;

    public Cuboid(double width, double length, double height) {
        super(width, length);
        if(height>=0){
            this.height = height;
        }
    }

    public double getHeight() {
        return height;
    }
    public double getVolume(){
        return getArea()*height;
    }
    
}