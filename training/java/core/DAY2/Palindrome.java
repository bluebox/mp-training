package training.java.core.DAY2;

public class Palindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(1001));
    }
    public static boolean isPalindrome(int number){
        int n=Math.abs(number);
        int reverse=0;
        while(n>0){
            int rem=n%10;
            reverse=reverse*10+rem;
            n=n/10;
        }
        if(Math.abs(number)==reverse){
            return true;
        }
        return false;
    }
}
