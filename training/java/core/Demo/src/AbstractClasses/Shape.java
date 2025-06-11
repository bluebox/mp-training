package AbstractClasses;

public abstract class Shape {
	private String color;

    
    public Shape(String color) {
        this.color = color;
    }

    
    public String getColor() {
        return color;
    }

    
    public abstract double calculateArea();

    public void displayInfo() {
        System.out.println("This is a " + color + " shape.");
    }

}
