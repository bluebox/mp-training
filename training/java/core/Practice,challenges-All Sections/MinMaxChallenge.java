import java.util.Scanner;

public class MinMaxChallenge {
    public static void main(String[] args) {
        int min1=Integer.MAX_VALUE;
        int max1=Integer.MIN_VALUE;
        Scanner sc=new Scanner(System.in);
        while(true)
        {
            try{
            System.out.println("Enter a number");
            int num=sc.nextInt();
                if(min1>num)
                min1=num;
                if(max1<num)
                max1=num;
            }
            catch(Exception e)
            {
                System.out.println("Invalid Input");
                break;
            }
            
        }
        if(min1!=Integer.MAX_VALUE && max1!=Integer.MIN_VALUE){
        System.out.println("Min : "+min1);
        System.out.println("Max : "+max1);}
    }
}
