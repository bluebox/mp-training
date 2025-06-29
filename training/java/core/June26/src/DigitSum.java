public class DigitSum {
    public static void main(String[] args) {
        System.out.println(sumDigit(125));
        System.out.println(sumDigit(1000));
        System.out.println(sumDigit(5));
        System.out.println(sumDigit(-1000));
    }
    public static int sumDigit(int number)
    {
        if ( number < 0){
            return -1;
        }
        int sum=0;
        while(number > 0)
        {
            int rem = number %10;
            sum+=rem;
            number = number / 10;
        }
        return sum;
    }
}
