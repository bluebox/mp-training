package corejavaday_Two;

public class Palindrome {
    public static void main(String[] args){
        System.out.println(isPalin(-1221));
        System.out.println(isPalin(100));
    }
    public static boolean isPalin(int num){
        int temp=num,sum=0;
        int k=num;
        if(num<0) {
            temp = -(num);
            k = -(num);
        }
        while(temp>0){
            int d=temp%10;
            sum=sum*10+d;
            temp/=10;
        }
        if(sum==k)
            return true;
        return false;
    }
}
