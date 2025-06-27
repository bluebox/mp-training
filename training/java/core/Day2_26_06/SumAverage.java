package Day2_26_06;
import java.util.Scanner;
public class SumAverage {
	public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;
        int count = 0;

        while (true) {
            boolean isInt = scanner.hasNextInt();

            if (isInt) {
                int number = scanner.nextInt();
                sum += number;
                count++;
            } else {
                break;
            }
        }

        long average = (count == 0) ? 0 : Math.round((double) sum / count);
        System.out.println("SUM = " + sum + " AVG = " + average);

        scanner.close();
    }
}
