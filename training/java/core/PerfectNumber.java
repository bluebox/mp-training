public class PerfectNumber {
    public static void isPerfectNumber(int n){
        int sum = 0;
        for (int i=1; i<n; i++){
            if(n%i == 0){
                sum+=i;
            }
            
        }
        if(sum == n){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }
    public static void main(String[] args) {
        isPerfectNumber(6);
    }
}
