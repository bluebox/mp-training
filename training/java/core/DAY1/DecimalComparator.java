public class DecimalComparator {
    public static void main(String[] args) {
        System.out.println(areEqualByThreeDecimalPlaces(2.54343,2.343434));
    }
    public static boolean areEqualByThreeDecimalPlaces(double d1,double d2){
        String s1=String.format("%.3f",d1);
        String s2=String.format("%.3f",d2);
        if(s1.equals(s2)){
            return true;
        }
        return false;
    }
}
