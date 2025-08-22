package corejavaday_Two;

public class YearsAndDays {

    public static void main(String[] args){
        printYearsAndDays(525600);
        printYearsAndDays(1051200);
        printYearsAndDays(561600);
    }

    public static void printYearsAndDays(long mins){
        int hrs=(int)(mins/60);
        int days=hrs/24;
        int years=days/365;
        int rem_days=days%365;
        System.out.println(years+" y and "+rem_days+" d");

    }
}
