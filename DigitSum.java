package corejavaday_Two;

public class DigitSum {
    public static void main(String[] args){
        System.out.println(sumDigit(125));
        System.out.println(sumDigit(1000));
    }
    public static int sumDigit(int num){
        int sum=0;
        while(num>0){
            int d=num%10;
            sum+=d;
            num/=10;
        }
        return sum;
    }
}
