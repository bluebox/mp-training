package corejavaday_Two;

public class LeapYear {

    public static void main(String[] args){
        System.out.println(isLeapYear(-1600));
        System.out.println(isLeapYear(1600));
        System.out.println(isLeapYear(2017));
        System.out.println(isLeapYear(2000));

    }
    public static boolean isLeapYear(int num){
        boolean res=false;
        if(num<1 || num>9999) {
            res=false;
        }
        else if(num%4==0){
            if(num%100==0 && num%400==0)
                res=true;
            else
                res=false;
        }
        return res;
    }
}
