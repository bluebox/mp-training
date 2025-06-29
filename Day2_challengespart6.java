


import java.util.InputMismatchException;
import java.util.Scanner;
public class Day2_challengespart6 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        while(true){
            
            
            System.out.println("enter a number or any char to quit");
            int num=0;
            try{
                num=sc.nextInt();
            }
            catch(InputMismatchException n){
                break;
            }
            
            
            if (num<min)min=num;
            else if(num>max)max=num;


        }
        System.out.println("max value is "+max+"min value is "+min);
    }


    public static void inputThenprint(){
        Scanner sc=new Scanner(System.in);
        int sum=0;
        int c=0;
        int num=0;
        while(true){
            
            try{
                num=sc.nextInt();
            }
            catch(InputMismatchException n){
                break;
            }
            sum+=num;
            c++;


        }
        System.out.println("sum is "+sum);

        System.out.println(("Avg is"+(sum)/c));
    }

    public static int getBucketcount(double w,double h,double areapb,int extrab){
        double totalarea=w*h;
        double remainingarea=totalarea-(areapb*extrab);
        double nbuckets=remainingarea/areapb;
        return (int)nbuckets;



    }
}


