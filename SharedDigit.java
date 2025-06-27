package corejavaday_Two;
import java.util.Scanner;
public class SharedDigit {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int firstnum=sc.nextInt();
        int secondnum=sc.nextInt();
        System.out.println(hasSharedDigit(firstnum,secondnum));
    }
    public static boolean hasSharedDigit(int num1,int num2) {
        int firstdig1 = num1/10;
        int lastdig1 = num1%10;
        int firstdig2 = num2/10;
        int lastdig2 = num2%10;
        if (num1 < 10 || num1 > 99 || num2 < 10 || num2 > 99){
            return false;
        }
        else{
            if(firstdig1==firstdig2 || firstdig1==lastdig2 || lastdig1==firstdig2 || lastdig1==lastdig2)
                return true;
        }
        return false;
    }
}
