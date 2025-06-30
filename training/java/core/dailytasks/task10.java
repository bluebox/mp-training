import java.util.Scanner;

public class task10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxNumber;
        
        do {
            System.out.print("Enter the maximum number for the range (must be <= 1000): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); 
            }
            maxNumber = scanner.nextInt();
            if (maxNumber > 1000) {
                System.out.println("Maximum number cannot exceed 1000. Please try again.");
            } else if (maxNumber < 2) { 
                System.out.println("Maximum number should be at least 2 to find primes. Please try again.");
            }
        } while (maxNumber > 1000 || maxNumber < 2);


        int primeCount = 0; 

        System.out.println("\nSearching for prime numbers up to " + maxNumber + "...");

        
        for (int i = 2; i <= maxNumber; i++) {
            
            if (isPrime(i)) {
                
                System.out.println("Found prime: " + i);

 
                primeCount++;

                
                if (primeCount == 3) {
                    System.out.println("\nSuccessfully found 3 prime numbers. Exiting loop.");
                    break; 
                }
            }
        }

        scanner.close(); 
        System.out.println("------------------------------------");
        System.out.println("Total prime numbers found before exiting: " + primeCount);
    }

    
    public static boolean isPrime(int number) {
        if (number <= 1) { 
            return false;
        }

        
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) { 
                return false;
            }
        }

        return true; 
}
}