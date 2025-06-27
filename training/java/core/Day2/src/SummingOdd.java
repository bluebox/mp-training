public class SummingOdd {
    public static void main(String[] args) {
        int n1=1,n2=5;
        System.out.println("the sum of odd numbers in the range "+sumOdd(n1,n2) );
    }
    public static int sumOdd(int n1, int n2){
        int sum=0;
        if(n2<=n1 || n1<0 ){
            return -1;
        }
        else{
            for (int i = n1; i <=n2; i++) {
                if(isOdd(i)){
                    sum+=i;
                }
            }
        }
        return sum;
    }
    public static boolean isOdd(int num){
        return num%2!=0;
    }
}
