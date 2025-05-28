package training.java.core.DAY2;

public class SumOfEven {
    
    public static void main(String[] args) {
        System.out.println(getEvenDigitSum(-22));
    }
    public static int getEvenDigitSum(int number){
        if(number<0){
            return -1;
        }
        int n=number;
        int sum=0;
        while(n>0){
            int rem=n%10;
            if(rem%2==0){
                sum+=rem;
            }            
            n=n/10;
        }
        
        return sum;
    }
}
