
import java.util.Scanner;
public class Day2_challenges {
    public static void main(String[] args) {
        //challenge1
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number to get Sum of digits");
        int number=sc.nextInt();
        System.out.println("the Sum of digits of that number is "+sumDigits(number));

        //challenge2 
        System.out.println("Enter the range to find even numbers");
        int range1=sc.nextInt();
        System.out.println("to ");
        int range2=sc.nextInt();
        System.out.println("Even numbers between "+range1+"to "+range2);
        int even_count=0,odd_count=0;
        for(int i=range1;i<=range2;i++){
            if(isEvenNumber(i)){
                even_count+=1;
                System.out.println(i);
            }
            else{
                odd_count+=1;
            }
            if(even_count==5){
                break;
            }
        }
        System.out.println("total no.of odd numbers are "+odd_count+" total no.of even number are "+even_count);

        //challenge3 
        System.out.println("Enter the range to find sum of odd numbers in that range ");
        int range3=sc.nextInt();
        System.out.println("to");
        int range4=sc.nextInt();
        System.out.println("the sum of odd numbers in that range are "+isOdd(range3, range4));
        

        //challenge4



        System.out.println("enter the range to find the sum of numbers divisible by 3 and 5");
        int range5=sc.nextInt();
        System.out.println("to");
        int range6=sc.nextInt();
        int sumofnumsdivby3and5=0;
        int countofnumdivby3and5=0;
        for(int i=range5;i<=range6;i++){
            if((i%3==0)&&(i%5==0)){
                countofnumdivby3and5+=1;
                sumofnumsdivby3and5+=i;
            }
            if (countofnumdivby3and5==5){
                break;
            }

        }
        System.out.println("sum of numbers divisible by 3 and 5 are "+sumofnumsdivby3and5);



        //challenge5 
        System.out.println("Enter the range to find the prime numbers");
        int range7=sc.nextInt();
        System.out.println("to");
        int range8=sc.nextInt();
        int countprime=0;
        
        for(int i=range7;i<=range8;i++){
            if (countprime==30)break;
            if(isPrime(i)){
                countprime+=1;
                System.out.println(i);
            }
        } 





        //challenge 6 






        System.out.println("Enter the amount in dollars ($)");
        float dollars=sc.nextFloat();
        System.out.println("Enter the percentage range");
        float range9=sc.nextFloat();
        System.out.println("to");
        float range10=sc.nextFloat();
        System.out.println("enter the amount to increment");
        float increment=sc.nextFloat();
        for(float i=range9;i<range10;i+=increment){
            System.out.println((dollars*(i))/100);

        }
        

    }
    public static int sumDigits(int number){
        if(number<0){
            return -1;
        }
        int sumofdigits=0;
        while(number>0){
            sumofdigits+=number%10;
            number/=10;

        }
        return sumofdigits;
    }
    public static boolean isEvenNumber(int number){
        if(number<0)return false;
        if(number%2==0)return true;
        return false;

    }
    public static int isOdd(int range3,int range4){
        if(range3<0 || range4<0 || range3>range4)return -1;
        int sumOfoddnumbers=0;
        for(int i=range3;i<=range4;i++){
            if(i%2!=0) sumOfoddnumbers+=i;
        }
        
        
        return sumOfoddnumbers;

    }
    public static boolean isPrime (int number) {
        if(number<2)return false;
        for(int i=2;i<number;i++){
            if(number%i==0)return false;
            
        }
        return true;
        
    }
}
