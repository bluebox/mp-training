public class RemainderOperatorWithDouble {
    public static void main(String[] args) {
        // Step 1 & 2: Create two double variables
        double firstValue = 20.00;
        double secondValue = 80.00;

        // Step 3: Add and multiply
        double result = (firstValue + secondValue) * 100.00;
        System.out.println("Total result = " + result); // Optional: just to see the value

        // Step 4: Find remainder when divided by 40.00
        double remainder = result % 40.00;
        System.out.println("Remainder = " + remainder); // Optional

        // Step 5: Check if remainder is 0.00
        boolean isNoRemainder = (remainder == 0.00);

        // Step 6: Output boolean result
        System.out.println("isNoRemainder = " + isNoRemainder);

        // Step 7: If not true, print message
        if (!isNoRemainder) {
            System.out.println("Got some remainder");
        }
    }
}
