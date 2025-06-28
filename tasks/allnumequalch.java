import java.util.Scanner;
public class allnumequalch {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter three numbers:");
        int a=sc.nextInt();
        int b=sc.nextInt();
        int c=sc.nextInt();
        System.out.println(new String(printqual(a,b,c)));
        
    }
    public static char[] printqual(int a,int b,int c){
        if(a<0 || b<0 || c<0){
            return "invalid value".toCharArray();
        }
        else if (a==b && b==c){
            return "All numbers are equal".toCharArray();


        }
        else if (a!=b && b!=c && c!=a){
            return "All are different".toCharArray();

        }
        else{
            return "Neither all are equal or different".toCharArray();
        }
    }
    
}
