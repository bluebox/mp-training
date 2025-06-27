import java.util.Scanner;

public class Valid {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int i=1;
        while(i<=5){
            System.out.println("enter number #"+i);
            String s=sc.nextLine();
            try{
                int n=Integer.parseInt(s);
                System.out.println("you entered "+n);
                i++;
            }
            catch (NumberFormatException nf){
                System.out.println("invalid");
            }
        }
    }
}
