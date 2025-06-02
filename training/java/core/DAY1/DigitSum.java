public class DigitSum {
    public static void main(String[] args) {
        System.out.println(sumDigits(124));
    }
    public static int sumDigits(int n){
        if(n<0){
            return -1;
        }
        int ans=n;
        while(ans>9){
            int sum=0;
            while(ans>0){
                sum=sum+ans%10;
                ans=ans/10;
            }
            ans=sum;
        }
        return ans;
    }
}
