import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int sum=0,i=0;
        long avg=0l;
        while (true){
            String s=sc.nextLine();
            try {
                int n=Integer.parseInt(s);
                sum+=n;
                i++;
            }
            catch (NumberFormatException nf){
                System.out.println("sum : "+sum +" average : "+(sum/i));
                break;
            }
        }
    }
}
