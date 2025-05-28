package training.java.core.DAY2;

import java.util.Scanner;

public class MinMax {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        double min=Double.MAX_VALUE,max=Double.MIN_VALUE;
        while(true){
            System.out.println("Entera number or any character to quit");
            String str=sc.nextLine();
            try{
                double num= Double.parseDouble(str);
                if(num<min){
                    min=num;
                }
                if(num>max){
                    max=num;
                }
            }catch(Exception e){
                break;
            }
        }
        System.out.println("Minimum number entered = "+min);
         System.out.println("maximum number entered = "+max);
    }
}
