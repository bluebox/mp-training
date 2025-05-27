public class LargestPrimeFactor{
    public static void main(String[] args){
        System.out.println(getLargestPrime(21));
        System.out.println(getLargestPrime(217));
        System.out.println(getLargestPrime(0));
        System.out.println(getLargestPrime(45));
        System.out.println(getLargestPrime(-1));

    }
    public static int getLargestPrime(int number){
        if (number<=0){return -1;}
        int result=0;
        for(int i=2;i<number;i++){
            if(number%i==0 && isPrime(i) && i>=result){
 result=i;
            }
        }return result;
    }
    public static boolean isPrime(int x){
        if (x<=1){return false;}
        for(int i=2;i<x;i++){if(x%i==0){return false;}}
        return true;
    }
}