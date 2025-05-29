package training.java.core.DAY2;

public class CylinderCircle {
    public static void main(String[] args) {
        Circle circle= new Circle(3.75);
        System.out.println(circle.getRadius());
        System.out.println(circle.getArea());
       Cylinder cylinder=new Cylinder(5.55,7.25);
       System.out.println(cylinder.getRadius());
       System.out.println(cylinder.getHeight());
       System.out.println(cylinder.getArea());
        System.out.println(cylinder.getVolume());
    }
}
class Circle{
    double radius;
    public Circle(double radius){                
        if(radius>=0){
            this.radius=radius;
        }
    }
    public double getRadius(){
        return this.radius;
    }
    public double getArea(){
        return Math.PI*radius*radius;
    }  
}
class Cylinder extends Circle{
    double height;
    public Cylinder(double radius,double height){
        super(radius);                
        if(height>=0){
            this.height=height;
        }
    }
    public double getHeight(){
        return this.height;
    }
    public double getVolume(){
        return getArea()*height;
    }  
}
