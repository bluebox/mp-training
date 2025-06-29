public class InRangeInterestCalculator {
    public static void main(String[] args) {
        calculateInterest(100);
    }
    public static void calculateInterest(double amount){
        for(double rate = 7.5; rate <= 10; rate+=0.25){
            System.out.println("Interest for amount "+amount+" at interest rate "+rate+"% is "+amount * (rate / 100));
        }
    }
}
