public class YearsAndDays {

    public static void printYearsAndDays(int mins){
        int mins_in_year = 24*60*365;
        int years = (int) mins / mins_in_year;
        int days = (int) (mins % mins_in_year ) / (24*60);

        System.out.printf("%d mins = %d y and %d d\n",mins,years , days);
    }
    public static void main(String[] args) {
        printYearsAndDays(525600);
        printYearsAndDays(561600);
        printYearsAndDays(1051200);
    }
}
