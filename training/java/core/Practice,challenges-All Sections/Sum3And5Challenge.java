public class Sum3And5Challenge {
    public static void main(String[] args) {
        int sum=0,counter=0;
        for(int i=1;i<=1000;i++)
        {
            if(i%3==0 && i%5==0)
            {
                System.out.println(i);
                sum+=i;
                counter++;
                if(counter==5)
                {
                    break;
                }
            }
        }
        System.out.println("Sum of first 5 multiples of 3 and 5 is "+sum);

    }
}
