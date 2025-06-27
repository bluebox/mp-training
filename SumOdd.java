package corejavaday_Two;

public class SumOdd {
    public static void main(String[] args){
        System.out.println(sumOdd(1,100));
        System.out.println(sumOdd(-1,100));
        System.out.println(sumOdd(100,100));
        System.out.println(sumOdd(13,13));
        System.out.println(sumOdd(100,-100));
        System.out.println(sumOdd(100,1000));
    }
    public static boolean isOdd(int num){
        if(num<0 || num%2==0)
            return false;
        return true;
    }
    public static int sumOdd(int start,int end){
        int sum=0;
        if(start>end || start<0 || end<0)
            return -1;
        else {
            for (int i = start; i <= end; i++) {
                if (isOdd(i))
                    sum += i;
            }
            return sum;
        }
    }
}
