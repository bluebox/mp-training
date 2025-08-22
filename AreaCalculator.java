package corejavaday_Two;
import java.lang.Math;
public class AreaCalculator {
    
    public static void main(String[] args){
        
        System.out.println(area(5.0));
        System.out.println(area(-1));
        System.out.println(area(5.0,4));
        System.out.println(area(-1.0,4.0));
    }
    public static double area(double radius) {
        if(radius<0) {
            return -1;
        }
        double res=3.14159265*radius*radius;
        return res;
    }
    
    public static double area(double leng,double width){
        if(leng<0 || width<0){
            return -1;
        }
        return (leng * width) ;
    }
}
