
public class PerfectThroughSumOfProperDivisors {

    public static void main(String[] args) {
        int[] testNumbers = {6, 28, 5, -1, 97};

        for (int num : testNumbers) {
            if (isPerfect(num)) {
                System.out.println(num + " is a perfect number.");
            } else {
                System.out.println(num + " is not a perfect number.");
            }
        }
    }

    public static boolean isPerfect(int n) {
        if (n < 1) {
            return false;
        }

        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }

        return sum == n;
    }
}
