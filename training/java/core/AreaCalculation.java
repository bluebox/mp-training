

public class AreaCalculation {

    public static void area(double value){

        if(value < 0){
            System.out.println("Invalid");
        }
        else 
        System.out.printf("area of circle is : %f\n" , 3.14*value*value);
    }

    public static void area(double len , double breadth){
        if (len < 0 || breadth < 0){
            System.out.println("Invalid");
        }
        else 
        System.out.printf("area of rectangle is : %f\n" , len*breadth);
    }
    public static void main(String[] args) {
        area(5);
        area(4,-1);
        area(3,2);
    }
}
