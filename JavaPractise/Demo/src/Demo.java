import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        
        Scanner sc= new Scanner(System.in);
        System.out.println("======== CHALLENGE - 1 ===========");
        System.out.println("Please enter number of pounds:");
        double pounds =sc.nextDouble();
        double kiloGrams= pounds*0.45359237d;
        System.out.println("The number of Kilograms are :"+ kiloGrams );
        System.out.println("=========================================");
        System.out.println();
        System.out.println("======== CHALLENGE - 2 ===========");
        double firstValue=20d;
        double secondValue=80d;
        double totalValue= (firstValue+secondValue)*100d;
        double rem = totalValue%40.00d;
        System.out.println("The remainder is :"+rem);
        boolean temp;
        temp = (rem%2==0)?(true):(false);
        
        if(temp==true){
            System.out.println("Get some remainder.");
        }
        System.out.println("=========================================");
        System.out.println();
        System.out.println("======== CHALLENGE - 3 (Methods & Functions) ===========");
        System.out.println("Enter the Value for Arithmetic operations :");
        double a=sc.nextDouble();
        double b=sc.nextDouble();
        System.out.println("The sum is "+add(a,b));
        System.out.println("The difference is "+sub(a,b));
        System.out.println("The Multiplication Value is "+mul(a,b));
        System.out.println("The Division value is "+div(a,b));

    }

    public static double add(double a ,double b){
        return a+b;
    }

    public static double sub(double a ,double b){
        return a-b;
    }

    public static double mul(double a ,double b){
        return a*b;
    }

    public static double div(double a ,double b){
        return a/b;
    }

}
