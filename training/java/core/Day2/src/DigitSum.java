public class DigitSum {
    public static void main(String[] args) {
        int num=-234;
        if(digi(num)<0){
            System.out.println("Given number is negative ");
        }
        else{
            System.out.println("the sum of digits  = "+digi(num) +" for the number "+num);
        }
    }
    public static int digi(int num){
        if(num<0){
            return -1;
        }
        int sum=0;
        while(num>0){
            sum+=num%10;
            num=num/10;
        }
        return sum;
    }
}
