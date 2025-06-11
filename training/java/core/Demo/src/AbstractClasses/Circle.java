package AbstractClasses;

public class Circle  extends Shape{
	
	private double radius;

    public Circle(String color, double radius) {
        super(color); 
        this.radius = radius;
    }

   
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double getRadius() {
        return radius;
    }

}
