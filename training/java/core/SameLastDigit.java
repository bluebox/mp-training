public class SameLastDigit {

    public static boolean checkRange(int a){
        return (a > 9 && a < 1001);
    }

    public static void hasSameLastDigit(int a,int b,int c){
        if(!checkRange(a) || !checkRange(b) || !checkRange(c)) System.out.println(false);
        else{
            if(a%10 == b%10 || b%10 == c%10 || c%10 == a%10){
                System.out.println(true);
            }
            else System.out.println(false);
        }
    }
    public static void main(String[] args) {
        hasSameLastDigit(12, 21, 1301);
    }
}
