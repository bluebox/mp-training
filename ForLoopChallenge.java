package corejavaday_Two;

public class ForLoopChallenge {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 35; i <= 70; i++) {
            if (count == 3)
                break;
            else if (isPrime(i)) {
                System.out.println(i + " is prime");
                count += 1;
            }
        }
    }
    public static boolean isPrime(int num){
        if(num<=2)
            return (num==2);
        else{
            for(int i=2;i<num;i++){
                if(num%i==0)
                    return false;
            }
        }
        return true;
    }

}
