import java.util.Scanner;

public class InteractiveCalculator {

    public static void inputThenPrintSumAndAverage() {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        long average = 0; 

        while (true) {
            try {
              
                int number = scanner.nextInt();
                sum += number;
                count++;
            } catch (java.util.InputMismatchException e) {
               
                System.out.println("SUM = " + sum + " AVG = " + average);
                break;
            } finally {
                
                if (scanner.hasNextLine()) {
                    scanner.nextLine();
                }
            }
            
            if (count > 0) {
                average = Math.round((double) sum / count); 
            } else {
                average = 0; // If no numbers entered, average is 0
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        inputThenPrintSumAndAverage();
    }
}
