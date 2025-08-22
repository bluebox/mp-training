package corejavaday_Two;
import java.lang.Math;
public class FirstLastSum {
    public static void main(String[] args){
        System.out.println(firstLastSum(252));
        System.out.println(firstLastSum(257));
        System.out.println(firstLastSum(5));
        System.out.println(firstLastSum(0));
        System.out.println(firstLastSum(-10));
    }
    public static int firstLastSum(int num){
        if(num<0)
            return -1;

        int last=num%10;
        int count=(int)(Math.log10(num));
        int first=(int)(num/Math.pow(10,count));
        return (first+last);
    }
}
