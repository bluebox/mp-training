public class task23 {

    
    public static boolean isPalindrome(int number) {
       
        int originalNumber = number;
        if (number < 0) {
            number = -number; // Work with the absolute value for reversal
        }

        int reverse = 0;
        int tempNum = number; 
        
        while (tempNum > 0) {
            int lastDigit = tempNum % 10; // Extract the last digit
            reverse = reverse * 10 + lastDigit; 
            tempNum /= 10; 
        }

        
        return (originalNumber >= 0 && originalNumber == reverse) || (originalNumber < 0 && -originalNumber == reverse);
    }

    public static void main(String[] args) {
       
        System.out.println("isPalindrome(-1221): " + isPalindrome(-1221)); // Expected: true
        System.out.println("isPalindrome(707): " + isPalindrome(707));     // Expected: true
        System.out.println("isPalindrome(11212): " + isPalindrome(11212)); // Expected: false
        System.out.println("isPalindrome(121): " + isPalindrome(121));     // Expected: true
        System.out.println("isPalindrome(1001): " + isPalindrome(1001));   // Expected: true
    }
}
