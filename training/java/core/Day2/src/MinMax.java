import java.util.Scanner;

public class MinMax {
    public static void main(String[] args) {
        int loopcount=0,min=0,max=0;
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("enter a number or any character to quit");
            String s=sc.nextLine();
            try {
                int n=Integer.parseInt(s);
                if(min>n){
                    min=n;
                }
                if(max<n){
                    max=n;
                }
                loopcount++;
            }
            catch (NumberFormatException  nf){
                break;
            }
        }
        if(loopcount>0){
            System.out.println("max : "+max+" min : "+min);
        }
        else {
            System.out.println("no valid element");
        }
    }
}
