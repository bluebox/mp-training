public class SameLast {
    public static void main(String[] args) {
        int num1=12,num2=23,num3=34;
        System.out.println(hasSameDigit(num1,num2,num3));
    }
    public static boolean hasSameDigit(int n1,int n2, int n3){
        if(isvalid(n1)&&isvalid(n2)&&isvalid(n3)){
            if((n1%10==n2%10) ||(n1%10==n3%10) ||(n2%10==n3%10)){
                return true;
            }
            else return false;
        }
        return false;
    }
    public static boolean isvalid(int num){
        return num>=10&&num<=1000;
    }
}
