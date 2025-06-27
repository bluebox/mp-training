public class SumofEven {
    public static void main(String[] args) {
        int num=12345;
        System.out.println("sum : "+getEvenSum(num));
    }
    public static int getEvenSum(int num){
        int sum=0;
        while(num>0){
            if((num%10)%2==0){
            sum+=num%10;}
            num=num/10;
        }
        return num<0?-1:sum;
    }
}
