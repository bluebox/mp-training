import java.util.Scanner;

public class Day2_challengespart4 {
    public static void main(String[] args) {
        //challenge 15
        System.out.println("Enter the palindrome");
        Scanner sc=new Scanner(System.in);
        int number=sc.nextInt();
        if(isPalindrome(number))System.out.println(number+"is palindrome");
        else
        System.out.println(number+"is not palindrome");



        //challenge16
        System.out.println("enter the number for sum of first and last digit");
        int num=sc.nextInt();
        System.out.println("sum of first and last digit is "+sumofFirstandLastdigit(num));


        //challenge17
        System.out.println("enter the number for the sum of all even digits");
        int num2=sc.nextInt();
        System.out.println("sum of all even digits is "+sumofAllevendigits(num2));


        //challenge18
        System.out.println("enter the numbers to find shared digits");
        int num3=sc.nextInt();
        int num4=sc.nextInt();
        System.out.println(sharedDigit(num3,num4));

        //challenge19
        System.out.println("enter the numbers to find the last common digits are equal or not");
        int num5=sc.nextInt();
        int num6=sc.nextInt();
        int num7=sc.nextInt();
        System.out.println(hassameLastDigit(num5,num6,num7));
        
    }
    public static boolean isPalindrome(int number){
        if(number<0)number=-number;
        String s="";
        String reverse="";
        while(number>0){
            int digit=number%10;
            number=number/10;
            s=s+digit;
            reverse=""+digit+reverse;

        }
        if(reverse.equals(s))return true;
        return false;
    }
    public static int sumofFirstandLastdigit(int num){
        if(num<0)return -1;
        if(num<10)return num*2;
        int lastdigit=num%10;
        while(num>=10){
            num=num/10;
        }
        return lastdigit+num;
    }
    public static int sumofAllevendigits(int num){
        if (num<0)return -1;
        int sum=0;
        while(num>0){
            int digit=num%10;
            num=num/10;
            if(digit%2==0)sum+=digit;
        }
        return sum;
    }
    public static boolean sharedDigit(int num,int num1){
        if ((num<10 || num>99)||(num1<10 || num1>99))
        return false;
        int a=num%10; 
        num/=10 ;
        int b=num1%10;
        num1/=10; 
        if(((a==b)||(a==num1))&&((num==num1)||(num==b)))return true;
        return false;
    } 
    public static boolean hassameLastDigit(int num,int num1,int num2){
        if ((num<10||num1<10||num2<10)||(num>1000||num1>1000||num2>1000)){
            return false;
        }
        int numld=num%10;
        int num1ld=num1%10;
        int num2ld=num2%10;
        if((numld==num1ld)||(numld==num2ld)||(num1ld==num2ld)){
            return true;
        }
        return false;
       

    }
}
