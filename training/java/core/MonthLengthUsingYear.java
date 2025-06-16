import java.util.*;
public class MonthLengthUsingYear{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter month(1-12)");
        int month=sc.nextInt();
        System.out.println("enter year");
        int year=sc.nextInt();
        MonthLengthUsingYear len=new MonthLengthUsingYear();
        int days=len.FindDays(month,year);
        if(days<0){
           System.out.println("Invalid input");
        }else{
           System.out.println("No.of days in "+month+" "+year+"="+days);
        }
    }
    public int FindDays(int month,int year){
         switch(month){
            case 1,3,5,7,8,10,12:return 31;
            case 2:
                 if((year%4==0 && year%100!=0)||year%400==0){
                    return 29;
                 }else{
                    return 28;
                 }
            case 4,6,9,11:return 30;
            default:return -1;
         }
    }
}


