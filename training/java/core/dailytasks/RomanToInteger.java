import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;  

public class RomanToInteger {

    
    private static final String ROMAN_NUMERAL_REGEX = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    public int romanToInt(String s) {
        
        Pattern pattern = Pattern.compile(ROMAN_NUMERAL_REGEX);
        
        Matcher matcher = pattern.matcher(s);

        if (!matcher.matches()) {
            System.out.println("Invalid Roman numeral: " + s); 
            return -1; 
                       
        }

        Map<Character, Integer> romanValues = new HashMap<>();
        romanValues.put('I', 1);
        romanValues.put('V', 5);
        romanValues.put('X', 10);
        romanValues.put('L', 50);
        romanValues.put('C', 100);
        romanValues.put('D', 500);
        romanValues.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            int currentValue = romanValues.get(s.charAt(i));

            if (i + 1 < s.length() && romanValues.get(s.charAt(i + 1)) > currentValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RomanToInteger converter = new RomanToInteger();

        System.out.print("Enter a Roman numeral: ");
        String romanNumeral = scanner.nextLine();

        
        String processedRomanNumeral = romanNumeral.toUpperCase();

        int integerEquivalent = converter.romanToInt(processedRomanNumeral);

        
        if (integerEquivalent != -1) {
            System.out.println("The integer equivalent of " + romanNumeral + " is: " + integerEquivalent);
        }

        scanner.close();
    }
}
