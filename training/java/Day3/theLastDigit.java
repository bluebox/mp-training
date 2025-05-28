public class theLastDigit {
    public static void main(String[] args) {
       // System.out.println(hasSameLastDigit(41,22,71));
       System.out.println(hasSameLastDigit(1, 111, 25));
        System.out.println(hasSameLastDigit(23, 32, 42));
        System.out.println(hasSameLastDigit(9, 99, 999));

    }
    public static boolean hasSameLastDigit(int a,int b,int c){
        return !(a%10 != b%10 && a%10 != c%10);
            
        }

    }

