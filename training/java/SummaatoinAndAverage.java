import java.util.Scanner;

public class SummaatoinAndAverage{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean valid=true;
        int b=0;
        int sum=0;
        int Count=0;
        while(valid){
            String a=scanner.nextLine();
                
                
                try{
                b=Integer.parseInt(a);
                valid=true;}
                catch (NumberFormatException e) {
                valid = false;}


                if(valid){
                    sum+=b;
                    Count++;


        }

    }System.out.print("SUM = "+sum+" AVG = "+(sum/Count));
    }
}