import java.util.Scanner;

public class SumAvg {

	    public static void main(String[] args) {
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
	            scanner.nextLine();
	        }

	        long average = (count == 0) ? 0 : Math.round((double) sum / count);
	        System.out.println("SUM = " + sum + " AVG = " + average);
	    }
}


