public class OverloadedMethodChallenge {
    public static void main(String[] args) {
        System.out.println(convertToCentimetres(12));
        System.out.println(convertToCentimetres(5,11));
    }
    public static double convertToCentimetres(int heightInInches)
    {
        return (double)heightInInches*2.54;
    }
    public static double convertToCentimetres(int heightInFeet,int heightInInches)
    {
        return convertToCentimetres(heightInFeet*12+heightInInches);
    }
}
