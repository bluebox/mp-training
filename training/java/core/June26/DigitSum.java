package June26;

public class DigitSum {
    public static void main(String[] args) {
        System.out.println("The sum of digits in number 1000 : "+sumDigits(1000));
        System.out.println("The sum of digits in number 125 : "+sumDigits(125));
        System.out.println("The sum of digits in number -10 : "+sumDigits(-10));
    }
    public static int sumDigits(int number){
        if(number<0) return -1;
        int sum=0;
        while (number>0){
            sum+=number%10;
            number/=10;
        }
        return sum;
    }
}
