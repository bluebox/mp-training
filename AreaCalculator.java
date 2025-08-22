public class AreaCalculator {
    public static void main(String[] args) {
        System.out.println("Area of Circle is :" + area(4.70));
        System.out.println("Area of Rectangle is :"+ area(4.5 , 7.0));
        System.out.println("Area of Circle is :" + area(-4.70));
        System.out.println("Area of Rectangle is :"+ area(-4.5 , 7.0));
    }

    public static double area(double radius){
        if(radius < 0){
            return -1.0;
        }
        else{
            return 3.14 * radius * radius;
        }
    }

    public static double area(double x,double y){
        if(x<0 || y<0){
            return -1.0;
        }
        else{
            return x*y;
        }
    }
}
