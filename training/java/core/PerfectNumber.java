public class PerfectNumber{
    public static void main(String args[])
    {
        if(isPerfect(6))
        {
            System.out.println(6+" is perfect number");
        }
    }
    public static boolean isPerfect(int num)
    {
        int sumOfFactors=0;
        for(int i=1;i<num;i++)
        {
            if(num%i==0){
                sumOfFactors+=i;
            }

        }
        return sumOfFactors==num; 
    }
}