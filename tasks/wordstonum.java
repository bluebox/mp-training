import java.util.Scanner;

public class wordstonum {
    public static String numberToWords(int n) {
        String[] units = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
        String[] tens = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

        if (n < 0 || n > 99)
            return "Number out of range";
        if (n < 20)
            return units[n];
        if (n % 10 == 0)
            return tens[n / 10];
        return tens[n / 10] + "-" + units[n % 10];
    }

    public static int wordsToNumber(String word) {
        String[] units = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
        String[] tens = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

        word = word.trim().toLowerCase();

        for (int i = 0; i < units.length; i++) {
            if (word.equals(units[i])) return i;
        }
        
        for (int i = 2; i < tens.length; i++) {
            if (word.equals(tens[i])) return i * 10;
        }
        
        if (word.contains("-")) {
            String[] parts = word.split("-");
            int ten = 0, unit = 0;
            for (int i = 2; i < tens.length; i++) {
                if (parts[0].equals(tens[i])) ten = i * 10;
            }
            for (int i = 0; i < units.length; i++) {
                if (parts[1].equals(units[i])) unit = i;
            }
            return ten + unit;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number (0-99):");
        int n = sc.nextInt();
        System.out.println("In words: " + numberToWords(n));

        sc.nextLine(); 
        System.out.println("Enter a word (zero to ninety-nine):");
        String word = sc.nextLine();
        System.out.println("In number: " + wordsToNumber(word));
    }
}