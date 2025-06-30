
import java.util.Scanner;
public class task1 {
    public static void main(String[] args) {
        Scanner i=new Scanner(System.in);
        System.out.println("enter the first number:");
        double a1=i.nextDouble();
        System.out.println("enter the second number:");
        double a2=i.nextDouble();
        
        
        double val=(a1+a2)*100.00d;
        double remain=val%40.00d;
        System.out.println(remain);
        boolean vd=true;
        if ( remain!=0d){
            vd=false;
        }
       
        boolean isTrue =vd ? true :false;
        System.out.println(isTrue);
    }}
