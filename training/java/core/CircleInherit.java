import java.util.*;
class Circle{
    double radius;
    double circleArea(){
       return Math.PI*radius*radius;
    }
    public Circle(double radius){
       this.radius=radius;
    }
}
class Cylinder extends Circle{
    double height;
    public Cylinder(double radius,double height){
       super(radius);
       this.height=height;
    }
    double cylinderArea(){
       return (2*Math.PI*radius*height)+(2*circleArea());
    }
}
public class CircleInherit{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Cylinder cylinder=new Cylinder(sc.nextDouble(),sc.nextDouble());
        System.out.println("Cylinder Area="+cylinder.cylinderArea());
    }
}




