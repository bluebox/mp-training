public class palindrome {
    public static void main(String[] args) {
        isPalindrome(5);
        isPalindrome(-1221);
        isPalindrome(705);
        isPalindrome(21221);
    }
    public static void isPalindrome(int n){
        int temp=n;
        int r=0;
        while(n!=0){
            r=r*10 + n%10;
            n=n/10;
        }
        System.out.print(temp +"is palindrome:"+ (temp==r));
    }
    }
