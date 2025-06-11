package AbstractClasses;

public class Rectangle extends Shape{
	
	private double length;
    private double width;

    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    
    @Override
    public double calculateArea() {
        return length * width;
    }

        public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

}
