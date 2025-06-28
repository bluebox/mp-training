import java.util.Scanner;
public class circle {
    private double radius;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter raius:");
        double radius=sc.nextDouble();
        circle c=new circle(radius);
        System.out.println("enter height:");
        double height =sc.nextDouble();
        System.out.println("area of cylinder: "+c.area());
        cylinder cy=new cylinder(radius,height);
        System.out.println("volume of cylinder :" + cy.volume());
        
    }

    public circle(double radius) {
        this.radius = radius<0 ? 0:radius;
    }
    public double getradius(){
        return radius;
    }
    public void setradius(double radius){
        this.radius = radius < 0 ? 0 : radius;
    }

    public double circ() {
        return 2 * Math.PI * radius;
    }
    public double area() {
        return Math.PI * radius * radius;
    }

    public static class cylinder extends circle{
        private double height;
        public cylinder(double radius,double height) {
            super(radius);
            this.height=height<0? 0: height;
        }
        public double getHeight(){
            return height;
        }
        public double volume(){
            return area() * height;
        }

    }
}
