/*
 	if area method has one parameter find area of circle
 	if area method has two parameter find area of rectangle
 */
public class AreaCalculatorForCirclesRectangles {
    public static void main(String[] args) {
        double result = area(5.0);
        if( result == -1){
            System.out.println("radius must be > 0");
        }
        else{
            System.out.println("area of circle is "+ result);
        }
        
        result = area(-1);
        if( result == -1){
            System.out.println("radius must be > 0");
        }
        else{
            System.out.println("area of circle is "+ result);
        }
        
        result = area(5.0,4.0);
        if( result == -1){
            System.out.println("radius must be > 0");
        }
        else{
            System.out.println("area of rectangle is "+ result);
        }
        
        result = area(-1,4.0);
        if( result == -1){
            System.out.println("radius must be > 0");
        }
        else{
            System.out.println("area of rectangle is "+ result);
        }
    }
    public static double area(double radius){
        if (radius < 0 )
        {
            return -1.0;
        }
        return (radius * radius * 22 / 7);
    }

    public static double area(double x, double y){
        if ( x <0 || y < 0){
            return -1.0;
        }
        return x * y;
    }
}
