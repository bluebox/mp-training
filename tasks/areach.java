import java.util.Scanner;
public class areach {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the radius of circle:");
        double radius=sc.nextDouble();
        double result=area(radius);
        if (result<0){
            System.out.println("invalid value");

        }
        else{
            System.out.println("area of circle is :"+result);
        }
        
    }
    public static double area(double radius){
        if (radius<0){
            return -1.0;
        }
        else{
            
            return (3.14*radius*radius);
        }

    }
    
}
