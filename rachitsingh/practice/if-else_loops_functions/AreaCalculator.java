import java.util.Scanner;
class AreaCalculator{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the radius of a circle: ");
        double radius = input.nextDouble();
        System.out.println("Area of the circle is " + area(radius) + " square units");
        
        System.out.println("Enter the length and width of a rectangle respectively: ");
        double length = input.nextDouble();
        double width = input.nextDouble();
        
        System.out.println("Area of the rectangle is " + area(length, width) + " square units");
        input.close();
    }
    public static double area(double radius)
    {
        if (radius < 0)
        {
            return -1.0;
        }
        else
        {
            return (double)(3.14159265359 * radius * radius);
        }
    }
    public static double area(double length, double width)
    {
        if(length < 0 || width < 0)
        {
            return -1.0;
        }
        else
        {
            return length * width;
        }
    }
}
