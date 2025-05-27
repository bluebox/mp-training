public class DigitSumChallenge {
    public static void main(String[] args) {
        System.out.println(getDigitSum(1234));
        System.out.println(getDigitSum(999));
        System.out.println(getDigitSumSimple(1234));
        System.out.println(getDigitSumSimple(999));
    }
    public static int getDigitSum(int number){
        if(number<0)
            return -1;
        if(number<10)
            return number;
        int sum=0;
        while(number>0)
        {
            sum+=number%10;
            number/=10;
        }
        return getDigitSum(sum);
    }
    public static int getDigitSumSimple(int number)
    {
        if(number<0)
        return -1;
        return (1+(number-1)%9);
    }
}
