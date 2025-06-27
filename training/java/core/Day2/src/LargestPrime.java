public class LargestPrime {
    public static void main(String[] args) {
        System.out.println(getLargestPrime(21));
    }
    public static int getLargestPrime(int num){
        for(int i=num;i>=1;i--){
            if(num%i==0){
                if(isprime(i)){
                    return i;
                }
            }
        }
        return -1;
    }
    public static boolean isprime(int num){
        for(int i=2;i<num/2;i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
