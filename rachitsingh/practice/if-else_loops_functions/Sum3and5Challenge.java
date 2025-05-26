
public class Sum3and5Challenge{
    public static void main(String [] args)
    {
        int sum = 0;
        int count = 0;
        int [] resultArray = new int[5];
        int j = 0;
        for(int i = 1; i <= 1000; i++)
        {
            if( i % 15 == 0)
            {
                resultArray[j] = i;
                sum += i;
                count++;
                j++;
            }
            if(count == 5)
            {
                break;
            }
        }
        System.out.println("5 numbers within range [1, 1000] which are divisible by both 3 and 5 are as follows: ");
        j -= 1;
        while(j >= 0)
        {
            System.out.print(resultArray[j] + ",");
            j--;
        }
        System.out.println();
        System.out.println("Sum of these numbers: " + sum);
    }
}