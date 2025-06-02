package training.java.core.DAY2;

import java.util.Scanner;

public class AvgCal {
    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }
    public static void inputThenPrintSumAndAverage(){
        Scanner sc= new Scanner(System.in);
        int sum=0,avg=0,cnt=0;
        while (true) {
            String str=sc.nextLine();
            try{
                int n=Integer.parseInt(str);
                cnt++;
                sum=sum+n;
                avg=sum/cnt;
            }catch(Exception e){
                System.out.println("SUM = "+sum+" AVG = "+avg);
                break;
            }

        }
    }
}
