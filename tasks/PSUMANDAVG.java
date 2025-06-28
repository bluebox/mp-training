import java.util.Scanner;
public class PSUMANDAVG {

    public static void pc() {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        int count = 0;
        while (true) {
            System.out.println("Enter a number:");
            if (sc.hasNextInt()) {
                int num = sc.nextInt();
                sum += num;
                count++;
            } else {
                break;
            }
        }
        int avg = (count == 0) ? 0 : Math.round((float) sum / count);
        System.out.println("SUM=" + sum + " AVG=" + avg);
    }

    public static void main(String[] args) {
        pc();
    }
}