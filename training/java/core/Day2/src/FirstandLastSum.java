public class FirstandLastSum {
    public static void main(String[] args) {
        int num=5;
        System.out.println("sum : "+flsum(num));
    }
    public static int flsum(int num){
        int r=0;
        if(num<0){
            return -1;
        }
        int temp=num;
        while(num>=10)
            num/=10;
        //System.out.println(num);

        r=temp%10;
        //System.out.println(r);
        return num+r;
    }
}
