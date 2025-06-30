public class ForLoopChallenge {

    public static void main(String[] args) {
        int primeCount = 0;

        for (int number = 10; number <= 1000; number++) {
            if (isPrime(number)) {
                System.out.println("Prime number found: " + number);
                primeCount++;

                if (primeCount == 3) {
                    System.out.println("Found 3 prime numbers. Exiting loop.");
                    break;
                }
            }
        }
    }

    // Method to check if a number is prime
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
