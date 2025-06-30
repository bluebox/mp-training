public class MiniChallenge {
    public static void main(String[] args) {
        double amount = 100.0;

        for (double rate = 7.5; rate <= 10.0; rate += 0.25) {
            double interest = calculateInterest(amount, rate);
            System.out.printf("Interest at %.2f%% on $%.2f = $%.2f%n", rate, amount, interest);
        }
    }

    public static double calculateInterest(double amount, double interestRate) {
        return amount * (interestRate / 100);
    }
}
