public class ForChallenge {
    public static void main(String[] args) {
        int count=0;
        for(int i=15;i<=75;i++){
            if(isPrime(i)){
                count++;
                System.out.println(i+" is prime number");
//                if(count==3){
//                    break;
//                }
            }
        }
    }
    public static boolean isPrime(int num){
        if(num<2){
            return false;
        }
        if(num==2){
            return  true;
        }
        else{
            for (int i = 2; i <num/2; i++) {
                if(num%i==0)
                    return false;
            }
        }
        return true;
    }
}
