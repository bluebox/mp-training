public class Palindrome {

    public static boolean isPalindrome(int n){
        String value = String.valueOf(n);

        System.out.println(value);
       

        if (n < 0) {
            value = value.substring(1);
        }
         int st = 0, end = value.length()-1;
        while(st < end){
            if (value.charAt(st) != value.charAt(end)) return false;
            st++;
            end--;
        }
        
        return true;
    }
    public static void main(String[] args) {
       System.out.println( isPalindrome(121));
        System.out.println(isPalindrome(-131));
    }
}
