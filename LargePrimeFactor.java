package corejavaday_Two;

public class LargePrimeFactor {
    public static void main(String[] args){
        int num=217,large=0;
        if(num<=0)
            System.out.println("-1");
        else {
            for (int i = 2; i < num; i++) {
                if (num % i == 0)
                    if (isPrime(i))
                        large = i;
            }
            System.out.println(large);
        }

    }
    public static boolean isPrime(int num){
        for(int i=2;i*i<=num;i++){
            if(num%i==0) {
                return false;
            }
        }
        return true;
    }
}
