import  java.util.Scanner;
public class monthleapyear {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the month:");
        int month=sc.nextInt();
        System.out.println("enter the year:");
        int year=sc.nextInt();
        System.out.println(getdaysinmonth(month, year));
        
    }
    public static boolean isLeapYear(int year){
        
        if (year<0 || year>9999){
            return false;
        }
        
        if (year%4==0 && year%100!=0){
            return true;
        }
        else if(year%400==0){
            return true;
        }
        else{
            return false;
        }
          

              
        
    }
    public static int getdaysinmonth(int month,int year){
        if (month<1 || month>12|| year<1 ||year>9999){
            return -1;
        }
        if (month==2){
            if (isLeapYear(year)){
                return 29;
            }
            else{
                return 28;
            }
        }
        if (month%2==0 && month<8 || month%2!=0 && month>7 || month==8 || month==10){
            return 30;
        }
        else{
            return 31;
        }

    }
    
}
