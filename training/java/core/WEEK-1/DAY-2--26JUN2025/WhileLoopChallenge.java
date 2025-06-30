public class WhileLoopChallenge {

    public static void main(String[] args) {
        int number = 5;
        int finishNumber = 20;
        int evenCount = 0;
        int oddCount = 0;

        while (number <= finishNumber) {
            if (isEvenNumber(number)) {
                System.out.println("Even number: " + number);
                evenCount++;

                // Stop once we found 5 even numbers
                if (evenCount == 5) {
                    break;
                }
            } else {
                oddCount++;
            }

            number++;
        }

        System.out.println("Total even numbers found = " + evenCount);
        System.out.println("Total odd numbers skipped = " + oddCount);
    }

    public static boolean isEvenNumber(int number) {
        return number % 2 == 0;
    }
}
