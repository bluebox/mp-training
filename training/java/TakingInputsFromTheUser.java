import java.util.Scanner;
public class TakingInputFromUser{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int sum=0;
        
        int b=0;
        boolean valid=false;
        for(int i=1;i<=5;i++){
            do{
                System.out.println("Enter number #"+i);
                String a=scanner.nextLine();
                try{Integer.parseInt(a);
                b=Integer.parseInt(a);
                valid=true;}
                catch (NumberFormatException e) {
                valid = false;
    }

            }while(!valid);
            sum=sum+b;
        }
            System.out.println("Sum= "+sum);
    }
    
}