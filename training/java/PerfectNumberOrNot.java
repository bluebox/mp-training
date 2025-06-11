
public class PerfectNumberOrNot{
    public static void main(String[] args) {
        System.out.println(isPerfectNumber(28));
        System.out.println(isPerfectNumber(121));
        System.out.println(isPerfectNumber(66));
        
    }
    public static boolean isPerfectNumber(int number){
        int sum=0;
        for (int i=2;i<number;i++){
            if(number%i==0){
                sum=sum+i;
            }
        }
        return sum==number;
    }

    
}