public class methods3 {
     public static boolean areEqualByThreeDecimalPlaces(double num1,double num2 ){
        int int1 = (int) (num1 * 1000);
        int int2 = (int) (num2 * 1000);
        return int1 == int2;
    }
    public static void main(String[] args) {
        System.out.println(areEqualByThreeDecimalPlaces(-3.1756, -3.175));
    }
}
