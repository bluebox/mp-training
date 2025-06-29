import java.util.Scanner;

public class Day2_challengespart5 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in); 
        //challenge20 
        System.out.println("enter the number to find the factors");
        int num=sc.nextInt();
        printFactors(num);

        //challenge21
        System.out.println("enter the numbers to find the gcd");
        int num1=sc.nextInt();
        int num2=sc.nextInt();
        int t=greatesCommondivisor(num1, num2);
        System.out.println(t);




        //challenge22
        System.out.println("enter the number to find the perfect number");
        int num3=sc.nextInt();
        if(perfectNumber(num3))System.out.println(num3+" is perfect number");
        else System.out.println((num3+" is not perfect number"));

        //challenge23
        System.out.println("enter the number to get noof digits");
        int num4=sc.nextInt();
        System.out.println(getDigitcount(num4));

        //challenge24 
        System.out.println("enter the number to get reverse");
        int num5=sc.nextInt();
        System.out.println(reverse(num5));

        //challenge25
        System.out.println("enter the number to get to words");
        int num6=sc.nextInt();
        System.out.println(numbertoWords(num6));

        //challenge26
        System.out.println(("enter the numbers"));
        int num7=sc.nextInt();
        int num8=sc.nextInt();
        int num9=sc.nextInt();
        System.out.println(canPack(num7,num8,num9));

        
        
    }
    public static void printFactors(int number){
        if(number<0){
            System.out.println("invalid");
            return;
        }
        for(int i=1;i<=number;i++){
            if(number%i==0)
            System.out.println(i);
        } 
        


    }
    public static int greatesCommondivisor(int num,int num1){
        if(num1==0)return num;
        return greatesCommondivisor(num1, num%num1);
    }
    public static boolean perfectNumber(int number){
        if(number<0){
            System.out.println("invalid");
            return false;
        }
        int sum=0;
        for(int i=1;i<number;i++){
            if(number%i==0)
            sum+=i; 
        } 
        if(sum==number)return true;
        return false;
        


    }
    public static int getDigitcount(int num){
        if(num<0)return -1;
        int c=0;
        while (num>0) {
            num/=10;
            c+=1;
            
        }
        return c;
    }
    public static int reverse(int num){
        boolean isneg=false;
        if(num<0){
            isneg=true;
            num=-num;
        }
        int rnum=0; 
        while(num>0){
            int digit=num%10;
            num/=10;
            rnum=rnum*10+digit;
        }
        if(isneg)return -rnum;
        return rnum;

    }
    public static String numbertoWords(int num){
        if(num<0)return "invalid";
        int rnum=reverse(num);
        String s="";
        while(rnum>0){
            int digit=rnum%10;
            rnum/=10;
            if(digit==1)s+="one";
            else if(digit==0)s+="zero";
            else if(digit==2)s+="two";
            else if(digit==3)s+="three";
            else if(digit==4)s+="four";
            else if(digit==5)s+="five";
            else if(digit==6)s+="six";
            else if(digit==7)s+="seven";
            else if(digit==8)s+="eight";
            else s+="nine";
        }
        return s;
    }
    public static boolean canPack(int num,int num1,int num2){
        if(num<0||num1<0||num2<0)
        return false;
        num=num*5;
        if((num+num1)>num2){
            return true;
        }
        return false;
    }
    public static void Diagonal(int num){
         for(int i=0;i<num;i++)
        System.out.print("*"); 
        System.out.println();
        for(int i=0;i<num-2;i++)
        {
            System.out.print("*");
            for(int j=0;j<num-2;j++){
                if(j==i||(j==num-2-i-1)){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
                
                
            }
            System.out.println("*");
            
        }
        for(int i=0;i<num;i++)
        System.out.print("*"); 
    }
}


