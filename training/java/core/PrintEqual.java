public class PrintEqual {

    public static void printEqual(int a,int b,int c){
        if(a < 0 || b < 0 || c<0){
            System.out.println("Invalid");

        }

        else if (a!=b && b!=c && a!=c){
            System.out.println("All numbers are different");
        }

        else if(a == b && b == c){
            System.out.println("All are equal");
        }

        else{
            System.out.println("Neither all are equal or different");
        }
    }
    public static void main(String[] args) {
        printEqual(1,1,1);
        printEqual(-1,0, 0);
        printEqual(1,2 ,2 );
    }
}
