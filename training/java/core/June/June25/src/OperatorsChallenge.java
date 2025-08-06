/*
Challenge:

Create two double variables: 20.00 and 80.00.

Add them and multiply the sum by 100.00.

Find the remainder when dividing the result by 40.00.

Create a boolean that's true if the remainder is 0.00, else false.

Print the boolean.

If false, print "Got some remainder".
*/

public class OperatorsChallenge {

    public static void main(String[] args) {

        double num1 = 20.00;
        double num2 = 80.00;

        double total = (num1 + num2) * 100.00;
        System.out.println("Total: " + total);

        double remainder = total % 40.00;
        System.out.println("Remainder: " + remainder);

        boolean isNoRemainder = (remainder == 0.00);
        System.out.println("Is remainder zero? " + isNoRemainder);

        if (!isNoRemainder) {
            System.out.println("Got some remainder");
        }
    }
}

