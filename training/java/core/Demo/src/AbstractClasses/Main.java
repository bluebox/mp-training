package AbstractClasses;

public class Main {

	public static void main(String[] args) {
		
		Circle myCircle = new Circle("Red", 5.0);
        Rectangle myRectangle = new Rectangle("Blue", 4.0, 6.0);

        
        Shape shape1 = myCircle;
        Shape shape2 = myRectangle;

        System.out.println("Circle");
        System.out.println("Color: " + shape1.getColor()); 
        System.out.println("Area: " + shape1.calculateArea()); 
        myCircle.displayInfo(); 

        System.out.println("Rectangle");
        System.out.println("Color: " + shape2.getColor()); 
        System.out.println("Area: " + shape2.calculateArea()); 
        myRectangle.displayInfo(); 
	}

}
