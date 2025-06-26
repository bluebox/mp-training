public class LeapYear {

    public static boolean isLeapYear(int yr){
        return ((yr % 4 ==0 && yr%100!=0) || yr%400 == 0);
    }
    public static void main(String[] args) {
        int year = 2024;

        System.out.println(isLeapYear(year));
    }
}
