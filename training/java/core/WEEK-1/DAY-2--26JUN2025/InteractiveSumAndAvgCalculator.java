import java.util.Scanner;

public class InteractiveSumAndAvgCalculator {

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int count = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                sum += number;
                count++;
            } else {
                break;
            }
        }

        int average = (count == 0) ? 0 : Math.round((float) sum / count);
        System.out.println("SUM = " + sum + " AVG = " + average);

        scanner.close();
    }

    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }
}
