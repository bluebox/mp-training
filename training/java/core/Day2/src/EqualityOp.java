public class EqualityOp {
    public static void main(String[] args) {
        int num1=1,num2=4,num3=3;
        System.out.println(printEqual(num1,num2,num3)) ;
    }

    public static String printEqual(int num1 , int num2 , int num3){
        if(num1<0 ||num2<0 ||num3<0){
            return "invalid";
        }
        if(num1==num2 && num1==num3){
            return "All are equal";
        }
        if((num1 !=num2 && num1==num3)||(num1 !=num2 &&num2==num3)){
            return "Neither all are equal or different";
        }
        if(num1!=num2&&num1!=num3&&num2!=num3)
            return "all are different";
        return "";
    }
}
