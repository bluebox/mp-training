package samplecodes;

public class WhileChallenge {

    public static void main(String[] args) {
        int number = 5;
        int finishNumber = 20;
        int sum = 0;
        int limit = 0;
        while (number <= finishNumber) {
            if (!isEvenNumber(number)) {
                number++;
                limit++;
            }
            if (limit <= 5) {
                System.out.println("Even number " + number);
                sum += number;
                number++;
                continue;
            }
            break;
        }
        System.out.println(sum + " is sum of the 5 numbers");
    }

    // Check Even Number
    public static boolean isEvenNumber(int n) {
        return n % 2 == 0;
    }
}
