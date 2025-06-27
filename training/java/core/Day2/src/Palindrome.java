public class Palindrome {
    public static void main(String[] args) {
        int num=13221;
        if(isPalindrome(Math.abs(num))){
            System.out.println(num+" is palindrome");
        }
        else{
            System.out.println("not palindrome");
        }
    }
    public static boolean isPalindrome(int num){
        int sum=0,temp=num;
        while(temp >0){
            int rem=temp%10;
            sum=sum*10+rem;
            temp/=10;
        }
        return sum==num;
    }
}
