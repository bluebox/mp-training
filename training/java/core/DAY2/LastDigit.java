package training.java.core.DAY2;

public class LastDigit {
    public static void main(String[] args) {
        System.out.println(hasSameLastDigit(9,99,999));
    }
    public static boolean hasSameLastDigit(int a,int b,int c){
        if(isValid(a)&&isValid(b)&&isValid(c)){
            if(a%10==b%10||a%10==c%10||b%10==c%10){
                return true;
            }
        }
        return false;
    }
    public static boolean isValid(int a){
        if(a<10||a>1000){
            return false;
        }
        return true;
    }
}
