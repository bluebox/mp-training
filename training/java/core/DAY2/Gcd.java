package training.java.core.DAY2;

public class Gcd {
    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisior(81,153));
    }
    public static int getGreatestCommonDivisior(int a,int b){
        if(a<10||b<10){
            return -1;
        }
        int n=Math.min(a, b);
        for(int i=n;i>0;i--){
            if(a%i==0&&b%i==0){
                return i;
            }
        }
        return -1;
    }
}
