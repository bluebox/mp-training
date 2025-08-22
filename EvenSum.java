package corejavaday_Two;

public class EvenSum {
    public static void main(String[] args){
        System.out.println(evenSum(123456789));
        System.out.println(evenSum(252));
        System.out.println(evenSum(-22));
    }
    public static int evenSum(int num){
        int sum=0;
        if(num<0)
            return -1;
        else{
            while(num>0){
                int d=num%10;
                if(d%2==0)
                    sum+=d;
                num/=10;
            }
        }
        return sum;
    }
}
