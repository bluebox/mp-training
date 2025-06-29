
import java.util.Scanner;

public class Day2_challengespart2 {
    public static void main(String[] args) {
        //challenge 7 (leap year) 

        Scanner sc=new Scanner(System.in);
        System.out.println("enter the year ");
        
        int year=sc.nextInt();
        
        if (isLeapyear(year))
        System.out.println("is leap year");
        else 
        System.out.println("not a leap year"); 
        System.out.println("Enter the month and year to get no of days ");
        int month=sc.nextInt(); 
        int year2=sc.nextInt();
        System.out.println("no.of days in that month is "+getDaysInMonths(month, year2));


        //challenge 8
        System.out.println("enter the number(0-9) to print it in words");
        int number=sc.nextInt();
        switch (number) {
            case 1:
                {System.out.println("one");
                break;}
            case 2:
                {System.out.println("two");
                break;}
            case 3:
                {System.out.println("three");
                break;}
            case 4:
                {System.out.println("four");
                break;}
            case 5:
                {System.out.println("five");
                break;}
            case 6:
                {System.out.println("six");
                break;}
            case 7:
                {System.out.println("seven");
                break;}
            case 8:
                {System.out.println("eight");
                break;}
            case 9:
                {
                    System.out.println("nine");
                    break;
                }
            default:
                {System.out.println("other");
                break;
        }}





        //challenge 9

        System.out.println("enter the character to convert it into NATO phonetic alphabet");
        String c=sc.next(); 
        switch (c) {
            case "A":
                {System.out.println("Able");
                break;}
            case "B":
                {System.out.println("Baker");
                break;}
            case "C":
                {System.out.println("Charlie");
                break;}
            case "D":
                {System.out.println("Dog");
                break;}
            case "E":
                {System.out.println("Easy");
                break;}
        
            default:
                {System.out.println("not found");
                break;}
        }


        //challenge 10 
        System.out.println("Enter true if it is summer or else false and enter the temperature of the day ");
        boolean summer=sc.nextBoolean();
        int temp=sc.nextInt();
        if(isCatplaying(summer, temp))System.out.println("cat is playing");
        else System.out.println("cat is not playing ");
        

        //challenge11
        System.out.println("enter 3 numbers to check equality ");
        int num1=sc.nextInt();
        int num2=sc.nextInt();
        int num3=sc.nextInt();
        printEqual(num1, num2, num3);






    }
    public static boolean isLeapyear(int year){ 
        if (year<1 || year>=9999)return false;
        if((year%4==0 && year%100!=0)  || year%400==0){
            return true;
        }
        return false;

    }
    public static int getDaysInMonths(int month,int year){
        if(month<1 || month>12 || year<=0)return -1;
        switch (month) {
            case 1,3,5,7,8,10,12:
                
                return 31;
            case 4,6,9,11:
                return 30;
            default:{
                if(isLeapyear(year))return 29;
                return 28;
            }

        }
    }
    public static boolean isCatplaying(boolean summer,int temp){
        if(summer && ((25<=temp)&&(temp<=45)))
            return true;
        else if((25<=temp)&&(temp<=35))
        return true; 
        
        return false;

    }
    public static void printEqual(int a,int b,int c){
        if(a<0 || b<0|| c<0){
            System.out.println("invalid");
        return ;}
        if(a==b&& b==c)
        {System.out.println("all are same");
    return;}
    if((a!=b && b!=c && c!=a)){
        System.out.println("all are different");
        return ;
    }
    
    System.out.println("neither are same nor different");
    return;
}
}