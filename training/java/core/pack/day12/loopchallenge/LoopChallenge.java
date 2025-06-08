package day12.loopchallenge;

public class LoopChallenge {

    public static void main(String[] args) {
        first3Primes();
    }


    public static void first3Primes(){
        int limit = 0;
        for (int i = 15; i<=100; i++){
            if(isPrime(i)){
                System.out.println(i + " is a prime number");
                limit++;
            }
            else if( limit==3 ){
                break;
            }
        }
    }


    public static boolean isPrime(int num){

        if(num <= 1){
            return false;
        }
        for(int i=2; i <= num/2; i++){
            if (num % i == 0){
                return false;
            }
        }
        return true;

    }
}
