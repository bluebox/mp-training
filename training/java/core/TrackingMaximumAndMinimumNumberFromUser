import java.util.Scanner;
public class MaximumAndMinimum{
    public static void main(String [] args){
        Scanner scanner =new Scanner(System.in);
        int Min=Integer.MAX_VALUE;
        int Max=Integer.MIN_VALUE;
        boolean valid=true;
        int b=0;
        while(valid){
            System.out.println("Enter number:");
                String a=scanner.nextLine();
                
                
                try{
                b=Integer.parseInt(a);
                valid=true;}
                catch (NumberFormatException e) {
                valid = false;}


                if(valid){
                    if (Max<=b){Max=b;}
                    if (Min>=b){Min=b;}
                }

        
    }System.out.println("Maximum number = "+Max+" and Minimum number ="+Min);
}
}