public class EqualDecimal {

    public static boolean hasThreeEqualDecimal(double a , double b){

        if (a > 0 && b < 0 ) return false ;
        if (a < 0 && b > 0)  return false;

        String value1 = String.valueOf(a);
        String value2 = String.valueOf(b);

        System.out.println(value1.substring(0,5));
        System.out.println(value2.substring(0,5));


        return value1.substring(0,5).equals(value2.substring(0,5));
    }
    public static void main(String[] args) {
        double a = 3.1234d;
        double b = 3.1237d;

        System.out.println(hasThreeEqualDecimal(a, b));
    }
}
