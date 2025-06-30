import java.util.*;

public class problem21 {
    static final String[] ones = {
        "zero", "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine"
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        if (num == 0) {
            System.out.println("zero");
            return;
        }

        if (num < 0) {
            System.out.print("minus ");
            num = -num;
        }

        String result = convert(num);
        System.out.println(result);
    }

    static String convert(int num) {
        StringBuilder sb = new StringBuilder();
        int[] digits = new int[String.valueOf(num).length()];
        int i = digits.length - 1;
        while (num > 0) {
            digits[i--] = num % 10;
            num /= 10;
        }
        for (int d : digits) {
            sb.append(ones[d]).append(" ");
        }
        return sb.toString().trim();
    }
}

