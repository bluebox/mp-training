public class OverLoading {
    public static void main(String[] args) {
        System.out.println(convertToCentimeters(2,10));
    }
    public static double convertToCentimeters(int inches){
        return inches*2.54;
    }
    public static double convertToCentimeters(int feets,int inches){
        int totalInches=feets*12+inches;
        return convertToCentimeters(totalInches);
    }
}
