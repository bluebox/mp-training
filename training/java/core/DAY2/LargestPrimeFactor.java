package training.java.core.DAY2;

public class LargestPrimeFactor {
    public static void main(String[] args) {
        System.out.println(getLargestPrime(21));
    }
    public static int getLargestPrime(int number){
        if(number <2){
            return -1;
        }
        for(int i=number;i>1;i--){
            if(number%i==0&&isPrime(i)){
                return i;
            }
        }
        return -1;
    }
    public static boolean isPrime(int number){
        if(number<2){
            return false;
        }
        for(int i=2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}
