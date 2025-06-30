import java.util.Scanner;

public class task9 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.print("Enter the principal amount: $");
        double principal = scanner.nextDouble();

        
        System.out.print("Enter the starting interest rate (e.g., 7.5): ");
        double startRate = scanner.nextDouble();

        System.out.print("Enter the ending interest rate (e.g., 10.0): ");
        double endRate = scanner.nextDouble();

    
        scanner.close();

        System.out.println("\nCalculating interest for a principal of $" + String.format("%.2f", principal) + ":\n");

        
        for (double rate = startRate; rate <= endRate; rate += 0.25) {
           
            System.out.println(String.format("$%.2f at %.2f%% interest = $%.2f",
                                             principal, rate, calculateInterest(principal, rate)));
        }
    }

   
    public static double calculateInterest(double amount, double interestRate) {
        return (amount * (interestRate /100));
}
}