package training.java.core.DAY2;

import java.util.Scanner;

public class ReadingUserInput {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int cnt=0,sum=0;
        while(cnt<5){
            System.out.println("Enter the number #"+(cnt+1));
            String str=sc.nextLine();
            try{
                int n=Integer.parseInt(str);
                cnt++;
                sum+=n;
            }catch(Exception e){
                System.out.println("Invalid number");
            }
        }
        System.out.println("sum is "+sum);
          
        
    }
}
