
public class Palindrome {
  public static boolean isPalindrome(int num) {
	  int original = num;
      int reversed = 0;

      while (num > 0) {
          int digit = num % 10;
          reversed = reversed * 10 + digit;
          num /= 10;
      }

      return original == reversed;

  }
  
  public static void main(String[] args) {
      int num = 12321;
      if (isPalindrome(num)) {
          System.out.println(num + " is a palindrome.");
      } else {
          System.out.println(num + " is not a palindrome.");
      }
  }

}
